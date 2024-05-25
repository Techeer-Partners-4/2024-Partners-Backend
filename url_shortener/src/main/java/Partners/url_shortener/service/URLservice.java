package Partners.url_shortener.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Partners.url_shortener.domain.URL;
import Partners.url_shortener.repository.URLrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class URLservice {

    private static final Logger logger = LoggerFactory.getLogger(URLservice.class);

    private static URLrepository urlRepository;

    @Autowired
    public URLservice(URLrepository urlRepository) {
        URLservice.urlRepository = urlRepository;
    }

    public static URL shortenURL(String originalURL){
        logger.info("Shortening URL: {}", originalURL);
        String shortURL = generateShortUrl(originalURL+"a");

        URL url = new URL();
        url.setOriginalURL(originalURL);
        url.setShortenURL(shortURL);
        return url;
    }

    public static Optional<URL> findByShortenUrl(String shortUrl) {
        return urlRepository.findByShortenURL(shortUrl);
    }

    private static String generateShortUrl(String originalUrl) {
        return Integer.toHexString(originalUrl.hashCode());
    }
}