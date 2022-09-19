package com.individual.project.agenstvo.repos;


import com.individual.project.agenstvo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameContains(String username);
}