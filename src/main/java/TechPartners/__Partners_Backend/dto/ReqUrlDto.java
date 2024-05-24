package TechPartners.__Partners_Backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ReqUrlDto {
    @NotEmpty
    private String originUrl;
}
