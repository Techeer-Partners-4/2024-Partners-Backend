package com.partners2024.urlshorter.controller;

import com.partners2024.urlshorter.entity.ShortLink;
import com.partners2024.urlshorter.repository.ShortLinkRepository;
import com.partners2024.urlshorter.service.ShortLinkService;
import java.lang.ProcessBuilder.Redirect;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/short-links")
@AllArgsConstructor
public class ShortLinkController {

  private final ShortLinkService shortLinkService;

  @PostMapping("/create")
  public ShortLink createShortLink(@RequestParam String originalUrl) {
    return shortLinkService.createShortLink(originalUrl);
  }

  @GetMapping("/{hash}")
  public RedirectView redirect(@PathVariable String hash) {
    Optional<ShortLink> shortLinkOptional = shortLinkService.getOriginalUrl(hash);
    if (shortLinkOptional.isPresent()) {
      ShortLink shortLink = shortLinkOptional.get();
      return new RedirectView(shortLink.getOriginalUrl());
    } else {
      return new RedirectView("/404");
    }
  }
}
