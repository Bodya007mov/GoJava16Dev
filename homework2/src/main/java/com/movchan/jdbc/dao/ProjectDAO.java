package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Developer;
import com.movchan.jdbc.domain.Project;
import com.movchan.jdbc.domain.Sex;
import com.movchan.jdbc.error.EntityNotFoundException;
import com.movchan.jdbc.service.dto.ProjectDTO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDAO extends GenericDAO<Project, Long> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Project> getAll() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from projects;");
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = getProjectFromResultSet(resultSet);
                projects.add(project);
            }
            return projects;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get projects from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Project> getById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from projects where id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Project project = getProjectFromResultSet(resultSet);
                return Optional.ofNullable(project);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get project from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("delete from projects where id = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot delete project from database. Caused by: " + e.getMessage());
        }
    }

    @Override
    public Optional<Long> create(Project project) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("insert into projects (name, creation_date, company_id, customer_id, cost) values (?, to_date(?, 'DD MM YYYY'), ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, project.getName());
            statement.setString(2, String.format("%td %<tm %<tY", project.getCreationDate()));
            statement.setLong(3, project.getCompanyId());
            statement.setLong(4, project.getCustomerId());
            statement.setBigDecimal(5, project.getCost());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                Long st = statement.getGeneratedKeys().getLong(1);
                return Optional.ofNullable(st);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot create project in database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Project project) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement statement = connection.prepareStatement("update projects set name = ?, creation_date = to_date(?, 'DD MM YYYY'), company_id = ?, customer_id = ?, cost = ? where id = ?;")) {
            statement.setString(1, project.getName());
            statement.setString(2, String.format("%td %<tm %<tY", project.getCreationDate()));
            statement.setLong(3, project.getCompanyId());
            statement.setLong(4, project.getCustomerId());
            statement.setBigDecimal(5, project.getCost());
            statement.setLong(6, project.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot update project in database. Caused by: " + e.getMessage());
        }
    }

    private Project getProjectFromResultSet(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getLong("id"));
        project.setName(resultSet.getString("name"));
        project.setCreationDate(resultSet.getDate("creation_date"));
        project.setCompanyId(resultSet.getLong("company_id"));
        project.setCustomerId(resultSet.getLong("customer_id"));
        project.setCost(resultSet.getBigDecimal("cost"));
        return project;
    }

    public Optional<Double> getTotalSalaryById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("select sum(d.salary)" +
                     "from developers d " +
                     "         join developers_projects dp on d.id = dp.developer_id " +
                     "         join projects p on dp.project_id = p.id " +
                     "where p.id = ? " +
                     "group by p.name;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Double totalSalary = resultSet.getDouble("sum");
                return Optional.ofNullable(totalSalary);
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get total salary from database. Caused by: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Developer> getDevelopersById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             PreparedStatement statement = connection.prepareStatement("select d.id as developer_id, d.name as developer_name, age, sex, salary " +
                     "from developers d " +
                     "         join developers_projects dp on d.id = dp.developer_id " +
                     "         join projects p on dp.project_id = p.id " +
                     "where p.id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Developer> developers = new ArrayList<>();
            while(resultSet.next()) {
                Developer developer = new Developer();
                developer.setId(resultSet.getLong("developer_id"));
                developer.setName(resultSet.getString("developer_name"));
                developer.setAge(resultSet.getInt("age"));
                developer.setSex(Sex.valueOf(resultSet.getString("sex").toUpperCase()));
                developer.setSalary(resultSet.getBigDecimal("salary"));
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Cannot get developers by project id from database. Caused by: " + e.getMessage());
        }
    }

    public List<ProjectDTO> getProjectDTOs() {
        try (Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select p.creation_date, p.name, count(dp.developer_id) " +
                    "from developers_projects dp " +
                    "       join projects p on dp.project_id = p.id " +
                    "group by p.name, p.creation_date;");
            List<ProjectDTO> projects = new ArrayList<>();
            while(resultSet.next()) {
                ProjectDTO projectDTO = new ProjectDTO();
                projectDTO.setName(resultSet.getString("name"));
                projectDTO.setCreationDate(dateFormat.parse(resultSet.getString("creation_date")));
                projectDTO.setDeveloperCount(resultSet.getInt("count"));
                projects.add(projectDTO);
            }
            return projects;
        } catch (SQLException | ParseException e) {
            throw new EntityNotFoundException("Cannot get projectsDTOs from database. Caused by: " + e.getMessage());
        }
    }
}
