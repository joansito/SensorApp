package com.example.sensorsapi.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> customValidationErrorHandling(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errors, request.getDescription(true));
        log.warn("Validation error occurred: {}", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Global fallback error handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex, WebRequest request) throws JsonParseException {
        if (ex instanceof JsonParseException) {
            throw (JsonParseException) ex;
        }

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                Collections.singletonList(ex.getMessage()),
                request.getDescription(false));

        log.error("An unexpected error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseException(JsonParseException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList("JSON parse error: " + ex.getOriginalMessage()),
                request.getDescription(false));
        log.error("JSON parse error: {}", ex.getOriginalMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Duplicate entry for sensorId and timestamp. Each sensor event must be unique.");
    }
}

