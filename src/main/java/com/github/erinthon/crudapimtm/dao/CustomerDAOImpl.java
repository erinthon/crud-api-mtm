package com.github.erinthon.crudapimtm.dao;

import com.github.erinthon.crudapimtm.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    private EntityManager entityManager;

    @Autowired
    public CustomerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll(String zipCode) {
        Session currentSession = entityManager.unwrap(Session.class);

        if(Objects.isNull(zipCode)) {
            Query<Customer> query = currentSession.createQuery("from Customer ", Customer.class);
            return query.getResultList();
        }

        Query<Object[]> query = currentSession.createQuery("from Customer c JOIN c.addresses a WHERE a.zipCode = :zipCode ", Object[].class);
        query.setParameter("zipCode", zipCode);

        List<Customer> customers = new ArrayList<>();
        query.getResultList().forEach(q -> {
            customers.add((Customer) q[0]);
        });

        return customers;
    }

    @Override
    public Customer findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Customer customer = currentSession.get(Customer.class, id);

        return customer;
    }

    @Override
    public void save(Customer customer) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public void update(Customer customer) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(customer);
    }

    @Override
    public void delete(Customer customer) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.delete(customer);
    }
}
