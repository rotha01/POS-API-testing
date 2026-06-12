package co.istad.demproductapisimple.advisor;

import co.istad.demproductapisimple.dto.productDto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestControllerAdvisor {

    //ExceptionHandler (NoSuchElementException)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse<?>> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder().message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status_code(HttpStatus.NOT_FOUND.value())
                        .build(),
                HttpStatus.NOT_FOUND

        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<?>> handleMethodValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error)->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message("Data is invalid")
                        .status_code(HttpStatus.BAD_REQUEST.value())
                        .errors(errors)
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse<?>> handleResourceAlreadyExistException(ResourceAlreadyExistException ex) {
        var response = ErrorResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status_code(HttpStatus.CONFLICT.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
