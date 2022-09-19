package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class UrData {
    @Id
    @GeneratedValue
    public Long idUrData;
    public void setIdUrData(Long idUrData) {
        this.idUrData = idUrData;
    }


    public UrData() {
    }

    public String getUrDataINN() {
        return UrDataINN;
    }

    public UrData(String urDataINN, String urDataOGRN, String urDataBIC) {
        UrDataINN = urDataINN;
        UrDataOGRN = urDataOGRN;
        UrDataBIC = urDataBIC;
    }

    public void setUrDataINN(String urDataINN) {
        UrDataINN = urDataINN;
    }

    public String getUrDataOGRN() {
        return UrDataOGRN;
    }

    public void setUrDataOGRN(String urDataOGRN) {
        UrDataOGRN = urDataOGRN;
    }

    public String getUrDataBIC() {
        return UrDataBIC;
    }

    public void setUrDataBIC(String urDataBIC) {
        UrDataBIC = urDataBIC;
    }

    public Long getIdUrData() {
        return idUrData;
    }
    @NotEmpty(message = "Поле не должно быть пустым, повторите попытку")
    @Size(min = 10, max = 10, message = "ИНН должен быть длинной в 10 символов, повторите попытку")
    public String UrDataINN;

    @NotEmpty(message = "Поле не должно быть пустым, повторите попытку")
    @Size(min = 13, max = 13, message = "ОГРН должен быть длинной в 13 символов, повторите попытку")
    public String UrDataOGRN;

    @NotEmpty(message = "Поле не должно быть пустым, повторите попытку")
    @Size(min = 9, max = 9, message = "БИК должен быть длинной в 9 символов, повторите попытку")
    public String UrDataBIC;

    @OneToOne(optional = true, mappedBy = "ClientUrData", fetch = FetchType.EAGER)
    public ClientPersonalData personalData;
}
