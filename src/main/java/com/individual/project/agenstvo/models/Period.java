package com.individual.project.agenstvo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

@Entity
public class Period {
    @Id
    @GeneratedValue
    public Long ID_Period;

    public void setID_Period(Long id_period) {
        this.ID_Period = id_period;
    }


    public Long getID_Period() {
        return ID_Period;
    }

    public Period() {
    }

    public Period(Date periodStart, Date periodEnd) {
        PeriodStart = periodStart;
        PeriodEnd = periodEnd;
    }

    public Date PeriodStart;

    public Date getPeriodStart() {
        return PeriodStart;
    }

    public void setPeriodStart(Date periodStart) {
        PeriodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return PeriodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        PeriodEnd = periodEnd;
    }

    public Date PeriodEnd;

    @OneToMany(mappedBy = "period", cascade=CascadeType.ALL,  fetch = FetchType.EAGER)
    public Set<Product> orders;

    public Collection<Product> getOrders() {
        return orders;
    }

    public void setOrders(Set<Product> orders) {
        this.orders = orders;
    }
}
