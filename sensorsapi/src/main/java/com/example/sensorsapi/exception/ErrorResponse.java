package com.example.sensorsapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;


    public ErrorResponse(HttpStatus status, List<String> messages, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.messages = messages;
        this.path = path;
    }
}

