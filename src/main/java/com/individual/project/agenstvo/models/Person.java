package com.individual.project.agenstvo.models;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue
    public Long ID_Person;

    public Person() {
    }

    public Person(String personName, String personPatronomyc, String personFname, Passport passport) {
        this.personName = personName;
        this.personPatronomyc = personPatronomyc;
        this.personFname = personFname;
        this.personAge = 18;
        this.passport = passport;
    }

    public Long getID_Person() {
        return ID_Person;
    }

    public void setID_Person(Long ID_Person) {
        this.ID_Person = ID_Person;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPatronomyc() {
        return personPatronomyc;
    }

    public void setPersonPatronomyc(String personPatronomyc) {
        this.personPatronomyc = personPatronomyc;
    }

    public String getPersonFname() {
        return personFname;
    }

    public void setPersonFname(String personFname) {
        this.personFname = personFname;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public  String personName;
    public  String personPatronomyc;
    public  String personFname;
    public  int    personAge;

   @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Passport passport;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToOne(optional = true, mappedBy = "person", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Client client;

    @OneToOne(optional = true, mappedBy = "person", cascade=CascadeType.ALL)
    public Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
