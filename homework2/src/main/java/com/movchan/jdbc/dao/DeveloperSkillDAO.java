package com.movchan.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeveloperSkillDAO {

    final String URL = "jdbc:postgresql://localhost:5432/homework";
    final String username = "postgres";
    final String password = "postgres007";

    public void create(Long developerId, Long skillId) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into developers_skills values (?, ?);")) {
            statement.setLong(1, developerId);
            statement.setLong(2, skillId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long developerId) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("delete from developers_skills where developer_id = ?;")) {
            statement.setLong(1, developerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
