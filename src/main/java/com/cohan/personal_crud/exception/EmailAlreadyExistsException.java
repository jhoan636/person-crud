package com.cohan.personal_crud.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super("El correo ya existe: " + message);
    }
}
