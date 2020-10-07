package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.ProjectDAO;
import com.movchan.jdbc.domain.Developer;
import com.movchan.jdbc.domain.Project;
import com.movchan.jdbc.error.EntityNotFoundException;
import com.movchan.jdbc.service.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDAO projectDAO;

    public List<Project> getAllProjects() {
        return projectDAO.getAll();
    }

    public Project getProjectById(Long id) {
        return projectDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find project with id = " + id));
    }

    public void deleteProjectById(Long id) {
        projectDAO.deleteById(id);
    }

    public void createProject(Project project) {
        projectDAO.create(project);
    }

    public void updateProject(Project project) {
        projectDAO.update(project);
    }

    public Double getTotalSalaryByProjectId(Long id) {
        return projectDAO.getTotalSalaryById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find resultSet with total salary"));
    }

    public List<Developer> getDevelopersByProjectId(Long id) {
        return projectDAO.getDevelopersById(id);
    }

    public List<ProjectDTO> getProjectDTOs() {
        return projectDAO.getProjectDTOs();
    }
}
