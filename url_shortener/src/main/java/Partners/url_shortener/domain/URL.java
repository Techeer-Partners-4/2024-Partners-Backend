package Partners.url_shortener.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class URL {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalURL;

    private String shortenURL;

    private String hash;

    @CreatedDate
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalURL() {
        return this.originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortenURL() {
        return shortenURL;
    }

    public void setShortenURL(String shortenURL) {
        this.shortenURL = shortenURL;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
