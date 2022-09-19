package com.individual.project.agenstvo.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long ID_Client;

    public void setID_Client(Long id_client) {
        this.ID_Client = id_client;
    }


    public Long getID_Client() {
        return ID_Client;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public User user;

    @ManyToOne(fetch = FetchType.EAGER)
    public ClientPersonalData personalData;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClientPersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(ClientPersonalData personalData) {
        this.personalData = personalData;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Product> getOrders() {
        return products;
    }

    public void setOrders(Set<Product> orders) {
        this.products = products;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Person person;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    public Set<Product> products;
}
