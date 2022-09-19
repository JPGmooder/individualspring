package com.individual.project.agenstvo.repos;

import com.individual.project.agenstvo.models.Passport;
import com.individual.project.agenstvo.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}