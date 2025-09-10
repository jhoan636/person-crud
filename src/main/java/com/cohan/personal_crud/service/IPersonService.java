package com.cohan.personal_crud.service;

import com.cohan.personal_crud.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<PersonDTO> getAllPersons();

    Optional<PersonDTO> getPersonById(Long id);

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO updatePerson(Long id, PersonDTO personDTO);

    void deletePerson(Long id);

    List<PersonDTO> searchPersonsByName(String searchTerm);

}
