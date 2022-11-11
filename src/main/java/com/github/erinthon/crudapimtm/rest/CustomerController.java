package com.github.erinthon.crudapimtm.rest;

import com.github.erinthon.crudapimtm.entity.Customer;
import com.github.erinthon.crudapimtm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> findAll(@RequestParam(required = false) String zipCode) {
        return customerService.findAll(zipCode);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return customer;
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        Customer c = customerService.findById(id);
        if(Objects.nonNull(c)) {
            customer.setId(c.getId());
            customerService.update(customer);
        }
        return customer;
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        Customer c = customerService.findById(id);
        customerService.delete(c);
    }
}
