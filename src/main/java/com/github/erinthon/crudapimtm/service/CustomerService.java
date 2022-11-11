package com.github.erinthon.crudapimtm.service;

import com.github.erinthon.crudapimtm.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll(String zipCode);

    public Customer findById(int id);

    public void save(Customer customer);

    public void update(Customer customer);

    public void delete(Customer customer);

}
