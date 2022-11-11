package com.github.erinthon.crudapimtm.dao;

import com.github.erinthon.crudapimtm.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> findAll(String zipCode);

    public Customer findById(int id);

    public void save(Customer customer);

    public void update(Customer customer);

    public void delete(Customer customer);

}