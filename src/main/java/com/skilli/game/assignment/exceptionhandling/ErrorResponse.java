package com.skilli.game.assignment.exceptionhandling;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String error;
    private final Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
    private int status;
    private String message;
}
