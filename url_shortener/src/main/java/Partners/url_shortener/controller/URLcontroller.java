package Partners.url_shortener.controller;

import Partners.url_shortener.domain.URL;
import Partners.url_shortener.service.URLservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class URLcontroller {

    @Autowired
    private URLservice ureService;

    @GetMapping("/")
    public String noboarding() {
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
