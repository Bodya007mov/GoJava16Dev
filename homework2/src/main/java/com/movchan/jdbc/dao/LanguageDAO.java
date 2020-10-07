package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Language;
import com.movchan.jdbc.error.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanguageDAO extends GenericDAO<Language, Long> {

    @Override
    public List<Language> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from languages;");
            List<Language> languages = new ArrayList<>();
            while(resultSet.next()) {
                Language language = getLanguageFromResultSet(resultSet);
                languages.add(language);
            }
            return languages;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get languages from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Language> getById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from languages where id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Language language = getLanguageFromResultSet(resultSet);
                return Optional.ofNullable(language);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get language from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("delete from languages where id = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete language from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Long> create(Language language) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("insert into languages (name) values (?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, language.getName());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create language in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Language language) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("update languages set name = ? where id = ?;")) {
            statement.setString(1, language.getName());
            statement.setLong(2, language.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot update language in database. Caused by: " + e.getMessage());
        }
    }

    private Language getLanguageFromResultSet(ResultSet resultSet) throws SQLException {
        Language language = new Language();
        language.setId(resultSet.getLong("id"));
        language.setName(resultSet.getString("name"));
        return language;
    }
}
