package com.partners2024.urlshorter.repository;

import com.partners2024.urlshorter.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {

  Optional<ShortLink> findByHash(String hash);

  List<ShortLink> findAllByDeletedFalseOrderByCreatedAtDesc();

}
