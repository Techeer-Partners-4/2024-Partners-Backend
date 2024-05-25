package TechPartners.__Partners_Backend.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResUrlDto {
    private String originUrl;
    private String shortUrl;
    private String hash;
    private LocalDateTime createdAt;
}
