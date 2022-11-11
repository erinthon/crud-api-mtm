package com.github.erinthon.crudapimtm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="zipCode")
    private String zipCode;

    @Column(name = "number")
    private int number;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.ALL})
    @JoinTable(
            name="customer_address",
            joinColumns=@JoinColumn(name="address_id"),
            inverseJoinColumns=@JoinColumn(name="customer_id")
    )
    private List<Customer> customers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
