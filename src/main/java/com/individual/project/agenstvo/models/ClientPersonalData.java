package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
public class ClientPersonalData {
    @Id
    @GeneratedValue
    public Long ID_PD;

    public void setID_PD(Long id_pd) {
        this.ID_PD = id_pd;
    }

    public Long getID_PD() {
        return ID_PD;
    }

    @NotNull
    @Min(value = 1, message = "Клиентов не может быть меньше 1")
    public  Integer pdEmployeesAmount;

    @NotNull
    @Size(min = 5, max = 100, message = "Название клиента должно содержать от 5 до 100 символов")
    public   String pdName;

    public ClientPersonalData() {
    }

    public ClientPersonalData(Integer pdEmployeesAmount, String pdName, UrData clientUrData, Address adressData) {
        this.pdEmployeesAmount = pdEmployeesAmount;
        this.pdName = pdName;
        ClientUrData = clientUrData;
        this.adressData = adressData;
    }

    @OneToMany(mappedBy = "personalData", fetch = FetchType.EAGER)
    public Set<Client> Client;

    @OneToOne(fetch = FetchType.EAGER)
   public UrData ClientUrData;

    public Integer getPdEmployeesAmount() {
        return pdEmployeesAmount;
    }

    public void setPdEmployeesAmount(Integer pdEmployeesAmount) {
        this.pdEmployeesAmount = pdEmployeesAmount;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public Collection<Client>  getClient() {
        return Client;
    }

    public void setClient(Set<Client> client) {
        Client = client;
    }

    public UrData getClientUrData() {
        return ClientUrData;
    }

    public void setClientUrData(UrData clientUrData) {
        ClientUrData = clientUrData;
    }

    public Address getAdressData() {
        return adressData;
    }

    public void setAdressData(Address adressData) {
        this.adressData = adressData;
    }

    @ManyToOne(fetch = FetchType.EAGER)
 public    Address adressData;
}
