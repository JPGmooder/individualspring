package com.individual.project.agenstvo.repos;

import com.individual.project.agenstvo.models.Client;
import com.individual.project.agenstvo.models.Period;
import com.individual.project.agenstvo.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByClient(Client client);

    List<Product> findAllByProductDescriptionContains(String text);

}
