package com.cohan.personal_crud.service;

import com.cohan.personal_crud.domain.Address;
import com.cohan.personal_crud.domain.Person;
import com.cohan.personal_crud.dto.AddressDTO;
import com.cohan.personal_crud.dto.PersonDTO;
import com.cohan.personal_crud.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements IPersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDTO> getAllPersons() {
        logger.debug("In getAllPersons");
        List<Person> persons = personRepository.findAll();
        logger.debug("Out getAllPersons");
        return persons.stream().map(this::convertPersonToPersonDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDTO> getPersonById(Long id) {
        logger.debug("In getPersonById");
        Optional<Person> person = personRepository.findById(id);
        logger.debug("Out getPersonById");
        return person.map(this::convertPersonToPersonDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDTO createPerson(PersonDTO personDTO) {
        logger.debug("In createPerson");
        Person person = convertPersonDTOToPerson(personDTO);
        person = personRepository.save(person);
        return convertPersonToPersonDTO(person);
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        logger.debug("In updatePerson");
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()) {
            throw new RuntimeException("Person with id " + id + " not found");
        }
        Person updatedPerson = person.get();
        updatedPerson.setFirstName(personDTO.getFirstName());
        updatedPerson.setLastName(personDTO.getLastName());
        updatedPerson.setEmail(personDTO.getEmail());
        updatedPerson.setAddress(convertAddressDTOToAddress(personDTO.getAddress()));
        updatedPerson = personRepository.save(updatedPerson);
        logger.debug("Out updatePerson");
        return convertPersonToPersonDTO(updatedPerson);
    }

    @Override
    public void deletePerson(Long id) {
        logger.debug("In deletePerson");
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()) {
            throw new RuntimeException("Person with id " + id + " not found");
        }
        // Depende de la logica de negocio si un borrado logico o fisico
        personRepository.deleteById(id);
        logger.debug("Out deletePerson");
    }

    @Override
    public List<PersonDTO> searchPersonsByName(String searchTerm) {
        logger.debug("In searchPersonsByName");
        logger.debug("Out searchPersonsByName");
        return List.of();
    }

    @Override
    public List<PersonDTO> findByBirthDateRange(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    private PersonDTO convertPersonToPersonDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setEmail(person.getEmail());
        dto.setAddress(convertAddressToAddressDTO(person.getAddress()));
        return dto;
    }

    private AddressDTO convertAddressToAddressDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        dto.setCountry(address.getPostalCode());
        return dto;
    }

    private Person convertPersonDTOToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail());
        person.setAddress(convertAddressDTOToAddress(personDTO.getAddress()));
        return person;
    }

    private Address convertAddressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        return address;
    }
}
