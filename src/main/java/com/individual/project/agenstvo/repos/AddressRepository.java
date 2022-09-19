package com.individual.project.agenstvo.repos;

import com.individual.project.agenstvo.models.Address;
import com.individual.project.agenstvo.models.Passport;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

}