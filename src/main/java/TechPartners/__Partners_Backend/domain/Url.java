package TechPartners.__Partners_Backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private Long id;

    @Column(name = "origin_url", nullable = false)
    private String originUrl;

    @Column(name = "short_url",nullable = false)
    private String shortUrl;

    @Column(name = "hash",nullable = false)
    private String hash;

    @CreatedDate // Insert
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


    private boolean deleted;

    @Builder
    public Url(String originUrl, String hash) {
        this.originUrl = originUrl;
        this.shortUrl = "http://localhost:8080/short-links/" + hash;
        this.hash = hash;
        this.createdAt = LocalDateTime.now();
        this.deleted = false;
    }

    public void deleteUrl() {
        this.deleted = true;
    }
}
