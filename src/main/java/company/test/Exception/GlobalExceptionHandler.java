package company.test.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException e){
        log.error("Data Access exception at: {}", LocalDateTime.now());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ElementNotFound.class)
    public ResponseEntity<?> handleElementNotFound(ElementNotFound e){
        log.error("Element not found exception at: {}", LocalDateTime.now());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedElement.class)
    public ResponseEntity<?> handleDuplicatedElement(DuplicatedElement e){
        log.error("Duplicated Element exception at: {}", LocalDateTime.now());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}