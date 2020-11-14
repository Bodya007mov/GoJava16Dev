package com.movchan.hibernate.dao;

import com.movchan.hibernate.domain.Customer;

public class CustomerDAO extends GenericDAO<Customer, Long> {

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }
}