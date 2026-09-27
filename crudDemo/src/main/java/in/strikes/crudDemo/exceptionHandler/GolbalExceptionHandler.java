package in.strikes.crudDemo.exceptionHandler;

import in.strikes.crudDemo.dto.ErrorResponseDto;
import in.strikes.crudDemo.dto.ValidationErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest servletRequest){
       ErrorResponseDto errorResponse = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                servletRequest.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(errorResponse);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ValidationErrorDto> handleMethodException (MethodArgumentNotValidException ex, HttpServletRequest servletRequest){
    Map<String,String> fieldErrors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(
            error -> fieldErrors.put(error.getField(), error.getDefaultMessage())
    );


    ValidationErrorDto validationErrorDto = new ValidationErrorDto(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getMessage(),
            servletRequest.getRequestURI(),
            fieldErrors
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(validationErrorDto);
}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("OOPs something went wrong");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("OOPs something went wrong");
    }
}
