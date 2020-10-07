package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Level;
import com.movchan.jdbc.domain.Skill;
import com.movchan.jdbc.error.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillDAO extends GenericDAO<Skill, Long> {

    @Override
    public List<Skill> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from skills;");
            List<Skill> skills = new ArrayList<>();
            while(resultSet.next()) {
                Skill skill = getSkillFromResultSet(resultSet);
                skills.add(skill);
            }
            return skills;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get skills from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Skill> getById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from skills where id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Skill skill = getSkillFromResultSet(resultSet);
                return Optional.ofNullable(skill);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get skill from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("delete from skills where id = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete skill from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Long> create(Skill skill) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into skills (level, language_id) values (cast(? as level_type), ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, skill.getLevel().toString().toLowerCase());
            statement.setLong(2, skill.getLanguageId());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create skill in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Skill skill) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("update skills set level = cast(? as level_type), language_id = ? where id = ?;")) {
            statement.setString(1, skill.getLevel().toString().toLowerCase());
            statement.setLong(2, skill.getLanguageId());
            statement.setLong(3, skill.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot update skill in database. Caused by: " + e.getMessage());
        }
    }

    private Skill getSkillFromResultSet(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setLevel(Level.valueOf(resultSet.getString("level").toUpperCase()));
        skill.setLanguageId(resultSet.getLong("language_id"));
        return skill;
    }
}
