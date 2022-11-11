package com.github.erinthon.crudapimtm.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name = "age")
    public int age;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date", nullable = false, updatable = false)
    private Date registrationDate = new Date();

    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.ALL})
    @JoinTable(
            name="customer_address",
            joinColumns=@JoinColumn(name="customer_id"),
            inverseJoinColumns=@JoinColumn(name="address_id")
    )
    private List<Address> addresses;

    public Customer() {}

    public Customer(Object customer) {
        Customer c = (Customer) customer;
        this.id = c.getId();
        this.name = c.getName();
        this.age = c.getAge();
        this.registrationDate = c.getRegistrationDate();
        this.addresses = c.getAddresses();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
