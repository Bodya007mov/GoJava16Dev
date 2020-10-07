package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.CustomerDAO;
import com.movchan.jdbc.domain.Customer;
import com.movchan.jdbc.error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public Customer getCustomerById(Long id) {
        return customerDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find customer with id = " + id));
    }

    public void deleteCustomerById(Long id) {
        customerDAO.deleteById(id);
    }

    public void createCustomer(Customer customer) {
        customerDAO.create(customer);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }
}
