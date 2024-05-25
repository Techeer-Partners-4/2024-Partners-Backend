package TechPartners.__Partners_Backend.exception;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@AllArgsConstructor
public class ResValidErrorDto {
    @NotEmpty
    private String message;

    @NotEmpty
    private int statusCode;

    public ResValidErrorDto(MethodArgumentNotValidException e){
        this.message = e.getMessage();
        this.statusCode = HttpStatus.BAD_REQUEST.value();
    }
}
