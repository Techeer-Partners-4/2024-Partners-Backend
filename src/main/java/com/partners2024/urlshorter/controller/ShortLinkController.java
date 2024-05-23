package com.partners2024.urlshorter.controller;

import com.partners2024.urlshorter.entity.ShortLink;
import com.partners2024.urlshorter.service.ShortLinkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/short-links")
@AllArgsConstructor
public class ShortLinkController {

  private final ShortLinkService shortLinkService;

  @PostMapping("/create")
  public ShortLink createShortLink(@RequestParam String originalUrl) {
    return shortLinkService.createShortLink(originalUrl);
  }
}
