package TechPartners.__Partners_Backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUrlDto {

    @NotEmpty
    @URL
    private String originUrl;
}
