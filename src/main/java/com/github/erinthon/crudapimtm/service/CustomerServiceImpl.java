package com.github.erinthon.crudapimtm.service;

import com.github.erinthon.crudapimtm.dao.CustomerDAO;
import com.github.erinthon.crudapimtm.entity.Address;
import com.github.erinthon.crudapimtm.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    @Transactional
    public List<Customer> findAll(String zipCode) {
        return customerDAO.findAll(zipCode);
    }

    @Override
    @Transactional
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        addressesZipCodeToMask(customer.getAddresses());
        customerDAO.save(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        customerDAO.delete(customer);
    }

    private void addressesZipCodeToMask(List<Address> addresses) {
        addresses.forEach(address -> {
            try {
                address.setZipCode(zipCodeToMask(address.getZipCode()));
            } catch (ParseException e) {
                // caso o projeto utilize log4j, logar essa exception
                throw new RuntimeException(e);
            }
        });
    }

    private String zipCodeToMask(String zipCode) throws ParseException {
        if(Objects.nonNull(zipCode)) {
            MaskFormatter maskFormatter = new MaskFormatter("#####-###");
            maskFormatter.setValueContainsLiteralCharacters(false);
            return maskFormatter.valueToString(zipCode);
        }
         return null;
    }

}
