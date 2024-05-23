package com.partners2024.urlshorter.controller;

import com.partners2024.urlshorter.entity.ShortLink;
import com.partners2024.urlshorter.repository.ShortLinkRepository;
import com.partners2024.urlshorter.service.ShortLinkService;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/links")
  public List<ShortLink> getAllLinks() {
    return shortLinkService.getAllLinks();
  }

  @DeleteMapping("/delete/{hash}")
  public ResponseEntity<String> deleteShortLink(@PathVariable String hash) {
    boolean isDeleted = shortLinkService.deleteShortLink(hash);
    if (isDeleted) {
      return ResponseEntity.ok("Short Link deleted successfully.");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Short Link not found.");
    }
  }

}
