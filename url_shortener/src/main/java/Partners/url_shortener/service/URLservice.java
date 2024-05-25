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
    public URLrepository urlRepository;

    public URL shortenURL(String originalURL){
        String shortURL = generateShortUrl(originalURL);
        URL url = new URL();
        url.getOriginalURL(originalURL);
        url.setShortenURL(shortURL);
        return urlRepository.save(url);
    }

    public Optional<URL> findByShortUrl(String shortUrl) {
        return urlRepository.findByShortenURL(shortUrl);
    }

    private String generateShortUrl(String originalUrl) {
        return Integer.toHexString(originalUrl.hashCode());
    }
}