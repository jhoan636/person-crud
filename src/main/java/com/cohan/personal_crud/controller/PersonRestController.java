package com.cohan.personal_crud.controller;

import com.cohan.personal_crud.dto.PersonDTO;
import com.cohan.personal_crud.service.IPersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/v1/persons")
@Validated
public class PersonRestController {
    private static final Logger logger = LoggerFactory.getLogger(PersonRestController.class);

    private final IPersonService personService;

    public PersonRestController(IPersonService personService) {
        this.personService = personService;
    }

    // Debo de tener un manejador de errores
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        logger.debug("GET /persons ");
        List<PersonDTO> persons;
        persons = personService.getAllPersons();
        logger.debug("GET out /persons ");
        return ResponseEntity.ok(persons);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        logger.debug("GET /persons/{}", id);
        PersonDTO person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        logger.debug("POST /persons");
        PersonDTO createdPerson = personService.createPerson(personDTO);
        logger.debug("POST out /persons");
        return ResponseEntity.ok(createdPerson);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long id,
            @Valid @RequestBody PersonDTO personDTO) {
        logger.debug("PUT /persons/{}", id);
        PersonDTO updatedPerson = personService.updatePerson(id, personDTO);
        logger.debug("PUT out /persons/{}", id);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        logger.debug("DELETE /persons/{}", id);
        // Borrado logico
        personService.deletePerson(id);
        logger.debug("DELETE out /persons/{}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PersonDTO>> searchPersonsByName(@RequestParam String name) {
        logger.debug("GET /persons/search?name={}", name);
        List<PersonDTO> persons = personService.searchPersonsByName(name);
        logger.debug("GET out /persons/search");
        return ResponseEntity.ok(persons);
    }
}
