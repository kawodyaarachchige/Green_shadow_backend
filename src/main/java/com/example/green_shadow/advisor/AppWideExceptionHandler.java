package com.example.green_shadow.advisor;

import com.example.green_shadow.exception.InvalidUserRoleException;
import com.example.green_shadow.exception.NoSuchEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AppWideExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception exception) {
        log.error(exception.getMessage()+" "+exception.getCause());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchEntityException.class})
    public ResponseEntity<String> handleException(NoSuchEntityException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler({InvalidUserRoleException.class})
    public ResponseEntity<String> handleException(InvalidUserRoleException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}

