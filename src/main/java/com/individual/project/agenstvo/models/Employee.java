package com.individual.project.agenstvo.models;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    public Long id;

    public void setId(Long id_employee) {
        this.id = id_employee;
    }


    public Long getId() {
        return id;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Doljnost doljnost;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Filial filial;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Person person;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public User user;

    public Doljnost getDoljnost() {
        return doljnost;
    }

    public Employee() {
    }

    public void setDoljnost(Doljnost doljnost) {
        this.doljnost = doljnost;
    }

    public Employee(Doljnost doljnost, Filial filial, Person person) {
        this.doljnost = doljnost;
        this.filial = filial;
        this.person = person;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Collection<Product> getOrders() {
        return orders;
    }

    public void setOrders(Set<Product> orders) {
        this.orders = orders;
    }

    @ManyToMany
    @JoinTable(name="employee_order",
            joinColumns=@JoinColumn(name="employee_id"),
            inverseJoinColumns=@JoinColumn(name="order_id"))
    public Set<Product> orders;
}
