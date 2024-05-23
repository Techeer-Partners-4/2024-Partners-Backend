package com.partners2024.urlshorter.service;

import com.partners2024.urlshorter.entity.ShortLink;
import com.partners2024.urlshorter.repository.ShortLinkRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import org.springframework.util.DigestUtils;

@Service
public class ShortLinkService {

  @Autowired
  private ShortLinkRepository shortLinkRepository;

  public ShortLink createShortLink(String originalUrl) {
    String hash = DigestUtils.md5DigestAsHex(originalUrl.getBytes(StandardCharsets.UTF_8));
    String shortUrl = "http://localhost:8080/short-links/" + hash;
    ShortLink shortLink = ShortLink.builder()
        .originalUrl(originalUrl)
        .hash(hash)
        .shortUrl(shortUrl)
        .createdAt(LocalDateTime.now())
        .build();
    return shortLinkRepository.save(shortLink);
  }

  public Optional<ShortLink> getOriginalUrl(String hash) {
    return shortLinkRepository.findByHash(hash);
  }


  public List<ShortLink> getAllLinks() {
    return shortLinkRepository.findAllByDeletedFalseOrderByCreatedAtDesc();
  }
}
