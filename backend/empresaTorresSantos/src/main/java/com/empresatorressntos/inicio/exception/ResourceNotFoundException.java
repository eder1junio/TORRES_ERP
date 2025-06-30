package com.empresatorressntos.inicio.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

}
