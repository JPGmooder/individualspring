package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Doljnost {
    @Id
    @GeneratedValue
    public Long id;

    @Column(unique = true)
    public  String doljnostName;

    public Doljnost() {
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy = "doljnost", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<Employee> employees;

    @Min(value = 1, message = "Зарплата должна быть больше или равна 1")
    @Max(value = 5000000, message = "Компания обанкротится при зарплате больше чем 5 миллионов рублей")
    public double Salary;

    public Doljnost(String doljnostName, double salary) {
        this.doljnostName = doljnostName;
        Salary = salary;
    }

    public String getDoljnostName() {
        return doljnostName;
    }

    public void setDoljnostName(String doljnostName) {
        this.doljnostName = doljnostName;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
