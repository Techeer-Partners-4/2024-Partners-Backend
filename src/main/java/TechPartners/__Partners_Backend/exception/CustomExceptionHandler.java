package TechPartners.__Partners_Backend.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @ApiResponse(
        responseCode = "400",description = "입력 데이터 형식 오류 발생"
    )
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResValidErrorDto> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException",e);
        ResValidErrorDto resValidErrorDto = new ResValidErrorDto(e);
        return ResponseEntity.status(resValidErrorDto.getStatusCode()).body(resValidErrorDto);
    }

    @ApiResponse(
        responseCode = "404",description = "해당 리소스가 존재하지 않음"
    )
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ResErrorDto> handleBusinessException(final BusinessException e) {
        log.error("handleBusinessException", e);
        ResErrorDto resErrorDto = new ResErrorDto(e);
        return ResponseEntity.status(resErrorDto.getStatusCode()).body(resErrorDto);
    }
}
