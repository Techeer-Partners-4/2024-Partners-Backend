package com.example.URLshortener.dao;
import com.example.URLshortener.bean.Url;
import lombok.val;
import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class UrlDaoService {
    private static List<Url> urlList = new ArrayList<>();
    private static int urlCount = 4;

    static {
        urlList.add(new Url(1, "https://www.google.com/search?newwindow=1&sca_esv=91bfb1353ebccd43&sca_upv=1&sxsrf=ADLYWIIRgsuxPqE-t_hZP5pgfTQZgTcLNA:1716293682403&q=%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4+%EC%82%AC%EB%82%98&source=lnms&uds=ADvngMgNG4qWEcyOv6mZ7d9R1NXiyzTc3JS7ooHd3g67c7wkkj-SVic402a0BCdOAthU5S8JeDo5BLelzd3jDyv4Ez6CH2BHBmHYUdxK4LmU7wjhOoHzXg-P6BLE-thTOPQf6h4UPdeM5m6nuWcIgUhNzlPuupVe-ToF0vI7hvtxnq1OkmkY1Ea7XGdYWe0hSXcesg5tTJktARw44aTIjOjt5GtiiCAZoXuCuXcwcK09VlNBQ4ZWPTFSdEsBd7PTWoT6osueZ7pwl26ldJV4EzRLy0GrWu8gfa16GXpNURpoKOGcWSfaSJA&sa=X&ved=2ahUKEwiA_JOa3J6GAxXlZvUHHVOXCPIQ0pQJegQIEBAB&biw=1440&bih=677&dpr=2","bf7ad4", new Date(), true));
        urlList.add(new Url(2, "https://www.google.com/search?newwindow=1&sca_esv=91bfb1353ebccd43&sca_upv=1&sxsrf=ADLYWIJKOlRw5n8wILnu1jK9ncG8IeVZSA:1716293856717&q=%EC%95%84%EC%9D%B4%EB%B8%8C+%EB%A6%AC%EC%A6%88&source=lnms&uds=ADvngMgwiuRe_0AnQUg9C1BclBAHsBw46bSRdfe2bH0jvSDuy2bsNBgT0Br0zVN0YasftmpzhETrHHrExX4isLiz6Ch5xpu84-PFb7E8ax9_gYhvzQugJ2VqgbyjzLLFQvztYCQJfFa3hNMksgdbMR896qeVggReHirC-91zH9_n-GcH3PO1Ger8G_qu7KeY0G30fW59NAZ9uUP9E2wGR6Weft2jBrsoY8l0TOgQKHDp3IjByTHtliekQF_cWDDNz7aXQBcNNmJbgno6qtOXk5onhdTXUJj7CgTxxIhAypj4B11sMHTN8k8&sa=X&ved=2ahUKEwiRv6Lt3J6GAxU5b_UHHWTaBJIQ0pQJegQIERAB&biw=1440&bih=677&dpr=2", "2aa109", new Date(), true));
        urlList.add(new Url(3, "https://www.google.com/search?newwindow=1&sca_esv=91bfb1353ebccd43&sca_upv=1&sxsrf=ADLYWIKnQfxp9mGdTiyV7AjL653_p0dlRg:1716293941410&q=%EC%97%90%EC%8A%A4%ED%8C%8C+%EB%8B%9D%EB%8B%9D&source=lnms&uds=ADvngMgwiuRe_0AnQUg9C1BclBAHBEWL-iwzi4k4ghM4PO_fex3hd4kag-gtQ8JWQtdgy6tITpDxSvkziZCUmlF3grrHna_MDFWGZoCx4PHmReQLWp0OKOjNj3Rsl9TaXu4EtGb9IWv72uAYlzwmABXAH1oSzC0AZGhgCH8FWio6A-T2gpBMtifOwnXGGbV9sm7UxNlQLyCyA_FmKydmgyPPxJVxQbkMf1HwYQExRBLCe5WNC_1XwNxXTh_X0uG4JfTn2pyWAriPkYWKlxYaLPTbyL72jLdg4f6kjzU3-lzvgsgKCLeCYpg&sa=X&ved=2ahUKEwjBwdSV3Z6GAxUGiK8BHXAsDCMQ0pQJegQIERAB&cshid=1716293944898764&biw=1440&bih=677&dpr=2", "57f27a", new Date(), true));
        urlList.add(new Url(4, "https://www.google.com/search?newwindow=1&sca_esv=91bfb1353ebccd43&sca_upv=1&sxsrf=ADLYWIJrKscZe4373AlxS8PuHAar_rQcPQ:1716294018621&q=%EB%B0%B1%EC%A7%80%ED%97%8C&source=lnms&uds=ADvngMhpsojwKe5eIOqT5IDaiLbe7aoHi2EhBSPlYGU5YAs1SUmVAWm-bwYb1tN6nECplO3vIsxZDhh-PuHpfdJS6V29Ub8-yqkL7BId8KmdR7uaL-O7wldYSvLhkuE5Bwi3aFampB5FK1_szvYdK6Btq6uXZg4cLAIRCXE-dbwz92yQZKyH0LGhVJ3AI6SI0um2oI6xv80S3h2ExXgXIsRp_9fx5gF3vjgKwuRYlcZJ48TUySMWSSNZ5RwYeVdzjMr8GBAzI25SB7E_EAqOz0WDGYdFT33JLLdOX95tK0SakJ-VIXzI9sI&sa=X&ved=2ahUKEwi2jL263Z6GAxWXbfUHHRUWDewQ0pQJegQIERAB&biw=1440&bih=677&dpr=2","e5122b", new Date(), true));

    }

    public List<Url> findAll() {

        List<Url> activeUrls = new ArrayList<>();
        for (Url url : urlList) {
            if (url.isActive()) {
                activeUrls.add(url);
            }
        }
        Collections.sort(activeUrls, Comparator.comparing(Url::getJoindate).reversed());

        return activeUrls;
    }

    public Url save(Url url) {
        if (url.getUrlid() == null) {
            url.setUrlid(++urlCount);
        }
        if (url.getJoindate() == null) {
            url.setJoindate(new Date());
        }

        try {
            String normalizedUrl = normalizeUrl(url.getOriginurl());
            url.setOriginurl(normalizedUrl);

            byte[] hashBytes = generateHash(url.getOriginurl());
            String hash = bytesToHex(hashBytes);
            url.setHash(hash.substring(0, 6));
            url.setActive(true);

        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            e.printStackTrace();
        }

        urlList.add(url);

        return url;
    }

    public static String normalizeUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        if (!url.contains("www.")) {
            url = url.replaceFirst("http://", "http://www.");
        }
        return url;
    }

    public static String bytesToHex(byte[] bytes) {
        BigInteger bigInt = new BigInteger(1, bytes);
        return bigInt.toString(16);
    }
    private byte[] generateHash(String origin) throws NoSuchAlgorithmException {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(origin.getBytes(StandardCharsets.UTF_8));
    }

    public Url findOne(int id, boolean active) {

        for (Url url : urlList) {
            if (url.getUrlid() == id && url.isActive()) {
                return url;
            }
        }

        return null;
    }

    public Url deleteById(int id) {
        for (Url url : urlList) {
            if (url.getUrlid() == id) {
                url.setActive(false);
                return url;
            }
        }
//        Iterator<Url> iterator = urlList.iterator();
//
//        while (iterator.hasNext()) {
//            Url url = iterator.next();
//
//            if(url.getId() == id) {
//                iterator.remove();
//                return url;
//            }
//        }

        return null;

    }

    public Url findByHash(String hash) {
        for (Url url : urlList) {
            if (url.getHash().equals(hash)) {
                return url;
            }
        }
        return null;
    }
}
