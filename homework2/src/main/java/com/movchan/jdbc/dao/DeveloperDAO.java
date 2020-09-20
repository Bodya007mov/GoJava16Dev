package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Developer;
import com.movchan.jdbc.domain.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperDAO extends GenericDAO<Developer, Long> {

    @Override
    public List<Developer> getAll() {

        try (Connection connection = DriverManager.getConnection(URL, username, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from developers");
            List<Developer> developers = new ArrayList<>();
            while (resultSet.next()) {
                Developer developer = getDeveloperFromResultSet(resultSet);
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Developer> getById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from developers where id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Developer developer = getDeveloperFromResultSet(resultSet);
                return Optional.of(developer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("delete from developers where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Long> create(Developer developer) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into developers (name, age, sex, salary) values (?, ?, cast(? as sex_type), ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setString(3, developer.getSex().toString().toLowerCase());
            statement.setDouble(4, developer.getSalary());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.of(st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Developer developer) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("update developers set name = ?, age = ?, sex = cast(? as sex_type), salary = ? where id = ?")) {
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setString(3, developer.getSex().toString().toLowerCase());
            statement.setDouble(4, developer.getSalary());
            statement.setLong(5, developer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Developer getDeveloperFromResultSet(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getLong("id"));
        developer.setName(resultSet.getString("name"));
        developer.setAge(resultSet.getInt("age"));
        developer.setSex(Sex.valueOf(resultSet.getString("sex").toUpperCase()));
        developer.setSalary(resultSet.getDouble("salary"));
        return developer;
    }
}
