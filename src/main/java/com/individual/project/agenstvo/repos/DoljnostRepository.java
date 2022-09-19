package com.individual.project.agenstvo.repos;

import com.individual.project.agenstvo.models.Doljnost;
import org.springframework.data.repository.CrudRepository;

public interface DoljnostRepository extends CrudRepository<Doljnost, Long > {

    Doljnost findByDoljnostName(String doljnostName);
}