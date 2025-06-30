package com.empresatorressntos.inicio.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ValidationErrorResponse.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> new ValidationErrorResponse.FieldError(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        ValidationErrorResponse response = new ValidationErrorResponse("Erro de validação nos campos", fieldErrors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}