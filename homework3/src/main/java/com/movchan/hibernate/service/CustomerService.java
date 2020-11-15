package com.movchan.hibernate.service;

import com.movchan.hibernate.dao.CustomerDAO;
import com.movchan.hibernate.domain.Customer;
import com.movchan.hibernate.service.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
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

    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customerDAO.create(customer);
    }

    public void updateCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        Long id = customerDTO.getId();
        customer.setId(id);
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customerDAO.update(customer, id);
    }
}
