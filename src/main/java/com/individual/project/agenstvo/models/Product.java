package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    public Long ID_Product;

    public Long getID_Product() {
        return ID_Product;
    }

    public void setID_Product(Long ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Client getClient() {
        return client;
    }
    public Product() {
    }


    public Product(String productDescription, double productPrice, boolean isAccepted, Period period, Set<Employee> employees, Client client) {
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.isAccepted = isAccepted;
        this.period = period;
        this.employees = employees;
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }








    @NotBlank(message = "Поле не должно содержать пустых знаков")
    @Size(min = 15, max = 100, message = "Описание заказа должно содержать от 15 до 100 символов")
    public String productDescription;

    @Min(value = 1500, message = "Минимальная сумма заказа - 1500")
    @Max(value = 999999999, message = "Некорректное значение суммы заказа")
    public double productPrice;

    public boolean isAccepted;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Period period;



    @ManyToMany
    @JoinTable(name="employee_order",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="employee_id"))
    public Set<Employee> employees;

    @ManyToOne(fetch = FetchType.EAGER)
    public Client client;
}
