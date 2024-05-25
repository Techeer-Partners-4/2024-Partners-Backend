package Partners.url_shortener.service;

import Partners.url_shortener.domain.URL;
import Partners.url_shortener.repository.URLrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class URLservice {

    @Autowired
    private static URLrepository urlRepository;

    public static URL shortenURL(String originalURL){
        String shortURL = generateShortUrl(originalURL);
        URL url = new URL();
        url.setOriginalURL(originalURL);
        url.setShortenURL(shortURL);
        return urlRepository.save(url);
    }

    public static Optional<URL> findByShortenUrl(String shortUrl) {
        return urlRepository.findByShortenURL(shortUrl);
    }

    private static String generateShortUrl(String originalUrl) {
        return Integer.toHexString(originalUrl.hashCode());
    }
}