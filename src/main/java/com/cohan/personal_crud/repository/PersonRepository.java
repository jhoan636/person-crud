package com.cohan.personal_crud.repository;

import com.cohan.personal_crud.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByFirstNameContainingIgnoreCase(String firstName);

    boolean existsByEmailIgnoreCase(String email);

}
