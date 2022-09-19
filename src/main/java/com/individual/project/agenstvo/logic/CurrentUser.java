package com.individual.project.agenstvo.logic;


import com.individual.project.agenstvo.models.*;

public class CurrentUser {
    private static CurrentUser instance;
    private CurrentUser(){}
    public static CurrentUser getInstance(){
        if(instance == null){
            instance = new CurrentUser();
        }
        return instance;
    }
    public User user;
    public Passport passport;
    public ClientPersonalData cpd;

    public Person person;
    public UrData urData;
    public Address address;

    public Filial filial;


    public User userToAdd;

    public Doljnost doljnost;



    public Client client;


    public static void ResetSettings() {
        instance = null;
    }
}
