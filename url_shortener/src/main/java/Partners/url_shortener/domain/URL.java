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

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getOriginalURL(){
        return originalURL;
    }
}
