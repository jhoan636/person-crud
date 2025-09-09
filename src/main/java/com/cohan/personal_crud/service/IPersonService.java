package com.cohan.personal_crud.service;

import com.cohan.personal_crud.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<PersonDTO> getAllPersons();

    Optional<PersonDTO> getPersonById(Long id);

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO updatePerson(Long id, PersonDTO personDTO);

    void deletePerson(Long id);


    List<PersonDTO> searchPersonsByName(String searchTerm);

    List<PersonDTO> findByBirthDateRange(LocalDate startDate, LocalDate endDate);

    boolean existsById(Long id);

}
