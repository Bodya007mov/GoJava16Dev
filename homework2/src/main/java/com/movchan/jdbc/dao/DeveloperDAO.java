package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Developer;
import com.movchan.jdbc.domain.Level;
import com.movchan.jdbc.domain.Sex;
import com.movchan.jdbc.error.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperDAO extends GenericDAO<Developer, Long> {

    @Override
    public List<Developer> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id as developer_id, name as developer_name, age, sex, salary from developers;");
            List<Developer> developers = new ArrayList<>();
            while (resultSet.next()) {
                Developer developer = getDeveloperFromResultSet(resultSet);
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developers from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Developer> getById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select id as developer_id, name as developer_name, age, sex, salary from developers where id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Developer developer = getDeveloperFromResultSet(resultSet);
                return Optional.ofNullable(developer);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developer from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("delete from developers where id = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete developer from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Long> create(Developer developer) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into developers (name, age, sex, salary) values (?, ?, cast(? as sex_type), ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setString(3, developer.getSex().toString().toLowerCase());
            statement.setBigDecimal(4, developer.getSalary());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create developer in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Developer developer) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("update developers set name = ?, age = ?, sex = cast(? as sex_type), salary = ? where id = ?;")) {
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setString(3, developer.getSex().toString().toLowerCase());
            statement.setBigDecimal(4, developer.getSalary());
            statement.setLong(5, developer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot update developer in database. Caused by: " + e.getMessage());
        }
    }

    private Developer getDeveloperFromResultSet(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getLong("developer_id"));
        developer.setName(resultSet.getString("developer_name"));
        developer.setAge(resultSet.getInt("age"));
        developer.setSex(Sex.valueOf(resultSet.getString("sex").toUpperCase()));
        developer.setSalary(resultSet.getBigDecimal("salary"));
        return developer;
    }

    public List<Developer> getDevelopersByLanguage(String language) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select d.id as developer_id, d.name as developer_name, age, sex, salary " +
                    "from developers d " +
                    "         join developers_skills ds on d.id = ds.developer_id " +
                    "         join skills s on ds.skill_id = s.id " +
                    "         join languages l on s.language_id = l.id " +
                    "where l.name = ?;")) {
            statement.setString(1, language);
            ResultSet resultSet = statement.executeQuery();
            List<Developer> developers = new ArrayList<>();
            while(resultSet.next()) {
                Developer developer = getDeveloperFromResultSet(resultSet);
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developers from database. Caused by: " + e.getMessage());
        }
    }

    public List<Developer> getDevelopersByLevel(Level level) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select d.id as developer_id, d.name as developer_name, age, sex, salary " +
                    "from developers d " +
                    "         join developers_skills ds on d.id = ds.developer_id " +
                    "         join skills s on ds.skill_id = s.id " +
                    "where s.level = cast(? as level_type);")) {
            statement.setString(1, level.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            List<Developer> developers = new ArrayList<>();
            while(resultSet.next()) {
                Developer developer = getDeveloperFromResultSet(resultSet);
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developers from database. Caused by: " + e.getMessage());
        }
    }
}
