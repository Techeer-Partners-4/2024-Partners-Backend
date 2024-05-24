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

}
