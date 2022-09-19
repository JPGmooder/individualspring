package com.individual.project.agenstvo.repos;

import com.individual.project.agenstvo.models.Filial;
import com.individual.project.agenstvo.models.Passport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilialRepository extends CrudRepository<Filial, Long> {
    List<Filial> findByFilialNameContains(String filialName);
}