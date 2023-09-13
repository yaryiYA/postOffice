package com.example.postoffice.exception;



import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> listErrors = new ArrayList<>();
        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldError) {
            String defaultMessage = error.getDefaultMessage();
            listErrors.add(defaultMessage);
        }

        ApiError<?> response = new ApiError<>( (HttpStatus) status, listErrors);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler({ EntityNotFoundException.class})
    protected ResponseEntity<ApiError<?>> notFoundException(Exception exception) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError<>(HttpStatus.NOT_FOUND,exception.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError<?>> saveException(DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError<>(HttpStatus.CONFLICT, exception.getCause().getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError<?>> unacceptableSituation(IllegalStateException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }
}
