package com.timeservice.exception;

import com.timeservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DateTimeParseException.class, DateTimeException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleDateTimeExceptions(Exception ex) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode("INVALID_TIMESTAMP")
                .errorMessage("Invalid ISO-8601 timestamp format provided: " + ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .errorMessage(ex.getMessage() != null ? ex.getMessage() : "An unexpected internal server error occurred.")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
