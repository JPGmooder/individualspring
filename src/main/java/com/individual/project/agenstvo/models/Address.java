package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue
    public Long idAddressData;

    public void setIdAddressData(Long idAddressData) {
        this.idAddressData = idAddressData;
    }


    public Long getIdAddressData() {
        return idAddressData;
    }
    @NotBlank(message = "Использование пустых полей запрещено")
    @Size(min = 15, max = 100, message = "Адрес должен быть длиннее 15 символов и короче 100, повторите попытку")
    String AddressDataLocation;

    public Address() {
    }

    @NotBlank(message = "Использование пустых полей запрещено")
    @Size(min = 15, max = 100, message = "Фактический адрес должен быть длиннее 15 символов и короче 100, повторите попытку")
    String AddressDataFakt;

    public Address(String addressDataLocation, String addressDataFakt, String addressDataName) {
        AddressDataLocation = addressDataLocation;
        AddressDataFakt = addressDataFakt;
        AddressDataName = addressDataName;
    }

    public String getAddressDataLocation() {
        return AddressDataLocation;
    }

    public void setAddressDataLocation(String addressDataLocation) {
        AddressDataLocation = addressDataLocation;
    }

    public String getAddressDataFakt() {
        return AddressDataFakt;
    }

    public void setAddressDataFakt(String addressDataFakt) {
        AddressDataFakt = addressDataFakt;
    }

    public String getAddressDataName() {
        return AddressDataName;
    }

    public void setAddressDataName(String addressDataName) {
        AddressDataName = addressDataName;
    }


    String AddressDataName;

    public Set<Filial> getFilials() {
        return filials;
    }

    public void setFilials(Set<Filial> filials) {
        this.filials = filials;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade=CascadeType.ALL)
    Set<Filial> filials;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "adressData", cascade=CascadeType.ALL)
    Set<ClientPersonalData> clients;
}
