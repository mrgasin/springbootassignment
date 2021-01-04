package com.alasdoo.developercourseassignment.controllers.exceptionHandlers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(composeErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    private ErrorResponse composeErrorResponse(HttpStatus status, String message) {
        log.error(request.getServletPath() +"  ---->  "+ message);
        ErrorResponse response = new ErrorResponse();
        response.setError(status.getReasonPhrase());
        response.setStatus(status.value());
        response.setMessage(message);
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));
        response.setPath(request.getServletPath());
        return response;
    }

    @Data
    private static class ErrorResponse {
        private String error;
        private int status;
        private String message;
        private Timestamp timestamp;
        private String path;
    }
}
