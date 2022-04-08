package com.skilli.game.assignment.exceptionhandling;

import com.skilli.game.assignment.securityconfig.exception.JwtFailureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GameExceptionHandler {

    @ExceptionHandler(JwtFailureException.class)
    public ResponseEntity<ErrorResponse> jwtAuthorizationError(JwtFailureException ex){
        log.error("Exception Occurred : "+ ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandling(Exception ex){
        log.error("Exception Occurred : "+ ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
