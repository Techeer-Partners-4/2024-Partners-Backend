package Partners.url_shortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Partners.url_shortener.domain.URL;
import Partners.url_shortener.service.URLservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shorten")
public class URLcontroller {

    private static final Logger logger = LoggerFactory.getLogger(URLcontroller.class);

    @Autowired
    public URLcontroller(URLservice urlService) {
    }

    @GetMapping("/")
    public String onboarding() {
        return "onboarding";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("url") String originalUrl, Model model) {
        URL url = URLservice.shortenURL(originalUrl);
        model.addAttribute("shortUrl", url.getShortenURL());
        return "result";
    }

    @GetMapping("/{shortenURL}")
    public String redirectiongURL(@PathVariable String shortenURL, Model model){
        Optional<URL> url = URLservice.findByShortenUrl(shortenURL);
        if (url.isPresent()) {
            return "redircet:" + url.get().getOriginalURL();
        } else {
            model.addAttribute("error", "URL not found");
            return "error";
        }
    }

}
