package com.individual.project.agenstvo.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE, MANAGER, CLIENT, ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
