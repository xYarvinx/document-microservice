package com.example.documentmicroservice.exception;


import com.example.documentmicroservice.dto.Error;
import com.example.documentmicroservice.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = ControllerExceptionHandler.class)
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        Error error = Error.builder()
                .message(e.getMessage())
                .build();

        ErrorResponse result = ErrorResponse.builder()
                .error(error)
                .build();

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(result);
    }
}
