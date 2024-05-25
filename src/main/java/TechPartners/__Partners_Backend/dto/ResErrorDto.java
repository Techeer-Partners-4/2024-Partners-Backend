package TechPartners.__Partners_Backend.dto;

import TechPartners.__Partners_Backend.exception.BusinessException;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResErrorDto {
    @NotEmpty
    private String message;

    @NotEmpty
    private int statusCode;

    public ResErrorDto(BusinessException e){
        this.message = e.getMessage();
        this.statusCode = e.getStatusCode();
    }
}
