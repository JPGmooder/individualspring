package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
public class Filial {
    @Id
    @GeneratedValue
    public Long ID_Filial;

    public Filial() {
    }

    public void setID_Filial(Long id_filial) {
        this.ID_Filial = id_filial;
    }


    public Long getID_Filial() {
        return ID_Filial;
    }

    public Filial(String filialName, Address address) {
        this.filialName = filialName;
        this.address = address;
    }

    @Size(min = 5, max = 50, message = "Название филиала должно быть длиннее 5 и короче 50 символов в длинне.")
    public String filialName;

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy = "filial", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<Employee> employees;

    public String getFilialName() {
        return filialName;
    }

    public void setFilialName(String filialName) {
        this.filialName = filialName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Filial(String filialName, Set<Employee> employees, Address address) {
        this.filialName = filialName;
        this.employees = employees;
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   public Address address;

}
