package com.cohan.personal_crud.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Persona no fue encontrada");
    }
}
