package com.individual.project.agenstvo.repos;

import com.individual.project.agenstvo.models.Client;
import com.individual.project.agenstvo.models.Passport;
import com.individual.project.agenstvo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByUser(User user);
}
