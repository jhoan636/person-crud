package com.cohan.personal_crud;

import com.cohan.personal_crud.domain.Person;
import com.cohan.personal_crud.dto.PersonDTO;
import com.cohan.personal_crud.repository.PersonRepository;
import com.cohan.personal_crud.service.PersonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    private AutoCloseable closeable;

    private Person person;
    private PersonDTO personDTO;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this); // NECESARIO
        person = new Person();
        person.setId(1L);
        person.setFirstName("Juan");
        person.setLastName("Pérez");
        person.setEmail("juan@example.com");

        personDTO = new PersonDTO();
        personDTO.setId(1L);
        personDTO.setFirstName("Juan");
        personDTO.setLastName("Pérez");
        personDTO.setEmail("juan@example.com");
    }

    @Test
    void testSavePerson() {
        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonDTO result = personService.createPerson(personDTO);

        assertNotNull(result);
        assertEquals(personDTO.getId(), result.getId());
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void testGetPersonByIdFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        PersonDTO result = personService.getPersonById(1L);

        assertNotNull(result, "Se esperaba un resultado no nulo");
        assertEquals(person.getEmail(), result.getEmail());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());

        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPersonByIdNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                com.cohan.personal_crud.exception.PersonNotFoundException.class,
                () -> personService.getPersonById(1L),
                "Se esperaba PersonNotFoundException"
        );
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllPersons() {
        List<Person> persons = List.of(person);
        when(personRepository.findAll()).thenReturn(persons);

        List<PersonDTO> result = personService.getAllPersons();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getFirstName());
        verify(personRepository, times(1)).findAll();
    }


    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close(); // PROTECCIÓN contra null
        }
    }
}
