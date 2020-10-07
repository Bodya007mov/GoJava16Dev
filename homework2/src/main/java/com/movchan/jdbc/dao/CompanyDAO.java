package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Company;
import com.movchan.jdbc.error.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyDAO extends GenericDAO<Company, Long> {

    @Override
    public List<Company> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from companies;");
            List<Company> companies = new ArrayList<>();
            while(resultSet.next()) {
                Company company = getCompanyFromResultSet(resultSet);
                companies.add(company);
            }
            return companies;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get companies from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Company> getById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from companies where id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Company company = getCompanyFromResultSet(resultSet);
                return Optional.ofNullable(company);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get company from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("delete from companies where id = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete company from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Long> create(Company company) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into companies (name, phone) values (?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getPhone());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create company in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Company company) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("update companies set name = ?, phone = ? where id = ?;")) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getPhone());
            statement.setLong(3, company.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot update company in database. Caused by: " + e.getMessage());
        }
    }

    private Company getCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        company.setPhone(resultSet.getString("phone"));
        return company;
    }
}
