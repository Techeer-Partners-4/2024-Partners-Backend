package TechPartners.__Partners_Backend.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResValidErrorDto> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException",e);
        ResValidErrorDto resValidErrorDto = new ResValidErrorDto(e);
        return ResponseEntity.status(resValidErrorDto.getStatusCode()).body(resValidErrorDto);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ResErrorDto> handleBusinessException(final BusinessException e) {
        log.error("handleBusinessException", e);
        ResErrorDto resErrorDto = new ResErrorDto(e);
        return ResponseEntity.status(resErrorDto.getStatusCode()).body(resErrorDto);
    }
}
