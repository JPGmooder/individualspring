package com.individual.project.agenstvo.repos;

import com.individual.project.agenstvo.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
