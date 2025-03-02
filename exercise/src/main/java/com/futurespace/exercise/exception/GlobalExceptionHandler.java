package com.futurespace.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<String> handlePersonaNotFoundException(PersonaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicatePersonaException.class)
    public ResponseEntity<String> handleDuplicatePersonaException(DuplicatePersonaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
