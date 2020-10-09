package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.DeveloperProjectKey;
import com.movchan.jdbc.error.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperProjectDAO extends GenericDAO<DeveloperProjectKey, Long> {

    @Override
    public List<DeveloperProjectKey> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from developers_projects;");
            List<DeveloperProjectKey> developerProjectKeys = new ArrayList<>();
            while (resultSet.next()) {
                DeveloperProjectKey developerProjectKey = getDeveloperProjectKeyFromResultSet(resultSet);
                developerProjectKeys.add(developerProjectKey);
            }
            return developerProjectKeys;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developer-project keys from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<DeveloperProjectKey> getById(Long developerId) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from developers_projects where developer_id = ?;")) {
            statement.setLong(1, developerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DeveloperProjectKey developerProjectKey = getDeveloperProjectKeyFromResultSet(resultSet);
                return Optional.ofNullable(developerProjectKey);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developer-project key from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> create(DeveloperProjectKey developerProjectKey) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into developers_projects values (?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, developerProjectKey.getDeveloperId());
            statement.setLong(2, developerProjectKey.getProjectId());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create developer-project key in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long developerId) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("delete from developers_projects where developer_id = ?;")) {
            statement.setLong(1, developerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete developer-project key from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public void update(DeveloperProjectKey developerProjectKey) {

    }

    private DeveloperProjectKey getDeveloperProjectKeyFromResultSet(ResultSet resultSet) throws SQLException {
        DeveloperProjectKey developerProjectKey = new DeveloperProjectKey();
        developerProjectKey.setDeveloperId(resultSet.getLong("developer_id"));
        developerProjectKey.setProjectId(resultSet.getLong("project_id"));
        return developerProjectKey;
    }
}
