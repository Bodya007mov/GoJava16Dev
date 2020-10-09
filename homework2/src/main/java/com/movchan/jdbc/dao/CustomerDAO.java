package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Customer;
import com.movchan.jdbc.error.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAO extends GenericDAO<Customer, Long> {

    @Override
    public List<Customer> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from customers;");
            List<Customer> customers = new ArrayList<>();
            while(resultSet.next()) {
                Customer customer = getCustomerFromResultSet(resultSet);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get customers from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Customer> getById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from customers where id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = getCustomerFromResultSet(resultSet);
                return Optional.ofNullable(customer);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get customer from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("delete from customers where id = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete customer from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Long> create(Customer customer) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into customers (name, phone) values (?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create customer in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("update customers set name = ?, phone = ? where id = ?;")) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());
            statement.setLong(3, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot update customer in database. Caused by: " + e.getMessage());
        }
    }

    private Customer getCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setPhone(resultSet.getString("phone"));
        return customer;
    }
}
