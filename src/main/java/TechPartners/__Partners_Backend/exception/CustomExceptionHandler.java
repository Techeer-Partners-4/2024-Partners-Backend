package TechPartners.__Partners_Backend.exception;

import TechPartners.__Partners_Backend.dto.ResErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ResErrorDto> handleBusinessException(final BusinessException e) {
        log.error("handleBusinessException", e);
        ResErrorDto resErrorDto = new ResErrorDto(e);
        return ResponseEntity.status(resErrorDto.getStatusCode()).body(resErrorDto);
    }
}
