package com.cohan.personal_crud.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Maneja errores de validación para cuerpos de solicitud
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put("error", error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Maneja errores de validación para parámetros de ruta y consulta
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                response.put("error", violation.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    // Maneja errores de integridad de datos, como entradas duplicadas
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEntry() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Violación de la integridad de los datos");
        return ResponseEntity.badRequest().body(response);
    }

    // Maneja excepciones en tiempo de ejecución personalizadas
    @ExceptionHandler({EmailAlreadyExistsException.class, PersonNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Maneja todas las demás excepciones no controladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Error inesperado porfavor contacte con administrador: ");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
