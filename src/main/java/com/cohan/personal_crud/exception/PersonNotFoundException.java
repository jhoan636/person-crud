package com.cohan.personal_crud.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Persona que deseas eliminar no existe");
    }
}
