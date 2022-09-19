package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity

public class Passport {
    public Passport() {
    }

    public Passport(String passport_Serial, String passport_Number) {
        Passport_Serial = passport_Serial;
        Passport_Number = passport_Number;
    }

    @Id
    @GeneratedValue
    public Long ID_Passport;

    public Long getID_Passport() {
        return ID_Passport;
    }

    public void setID_Passport(Long ID_Passport) {
        this.ID_Passport = ID_Passport;
    }

    public String getPassport_Serial() {
        return Passport_Serial;
    }

    public void setPassport_Serial(String passport_Serial) {
        Passport_Serial = passport_Serial;
    }

    public String getPassport_Number() {
        return Passport_Number;
    }

    public void setPassport_Number(String passport_Number) {
        Passport_Number = passport_Number;
    }


    @NotEmpty(message = "Серия паспорта может содержать только 4 цифры, повторите попытку")
    @Size(max = 4, min = 4, message = "Серия паспорта может содержать только 4 цифры, повторите попытку")
    public String Passport_Serial;

    @NotEmpty(message = "Номер паспорта может содержать только 6 цифр, повторите попытку")
    @Size(max = 6, min = 6, message = "Номер паспорта может содержать только 6 цифр, повторите попытку")
    public String Passport_Number;


    @OneToOne(optional = true, mappedBy = "passport", fetch = FetchType.EAGER)
    public Person person;




}
