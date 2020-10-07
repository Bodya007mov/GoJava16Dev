package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.DeveloperSkillKey;
import com.movchan.jdbc.error.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperSkillDAO extends GenericDAO<DeveloperSkillKey, Long>{

    @Override
    public List<DeveloperSkillKey> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from developers_skills;");
            List<DeveloperSkillKey> developerSkillKeys = new ArrayList<>();
            while (resultSet.next()) {
                DeveloperSkillKey developerSkillKey = getDeveloperSkillKeyFromResultSet(resultSet);
                developerSkillKeys.add(developerSkillKey);
            }
            return developerSkillKeys;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developer-skill keys from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<DeveloperSkillKey> getById(Long developerId) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from developers_skills where developer_id = ?;")) {
            statement.setLong(1, developerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DeveloperSkillKey developerSkillKey = getDeveloperSkillKeyFromResultSet(resultSet);
                return Optional.ofNullable(developerSkillKey);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developer-skill keys from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> create(DeveloperSkillKey developerSkillKey) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into developers_skills values (?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, developerSkillKey.getDeveloperId());
            statement.setLong(2, developerSkillKey.getSkillId());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create developer-skill keys in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long developerId) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("delete from developers_skills where developer_id = ?;")) {
            statement.setLong(1, developerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete developer-skill keys from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public void update(DeveloperSkillKey developerSkillKey) {

    }

    private DeveloperSkillKey getDeveloperSkillKeyFromResultSet(ResultSet resultSet) throws SQLException {
        DeveloperSkillKey developerSkillKey = new DeveloperSkillKey();
        developerSkillKey.setDeveloperId(resultSet.getLong("developer_id"));
        developerSkillKey.setSkillId(resultSet.getLong("skill_id"));
        return developerSkillKey;
    }
}
