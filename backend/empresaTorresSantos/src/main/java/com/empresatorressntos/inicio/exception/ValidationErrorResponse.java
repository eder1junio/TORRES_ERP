package com.empresatorressntos.inicio.exception;

import java.util.List;

public class ValidationErrorResponse {
    private String message; 
    private List<FieldError> errors; 

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(String message, List<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    
    public static class FieldError {
        private String field;  
        private String error;  

        public FieldError() {
        }

        public FieldError(String field, String error) {
            this.field = field;
            this.error = error;
        }

        // Getters e Setters
        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

}
