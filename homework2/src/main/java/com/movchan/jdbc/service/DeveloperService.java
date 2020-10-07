package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.DeveloperDAO;
import com.movchan.jdbc.domain.Developer;
import com.movchan.jdbc.domain.DeveloperProjectKey;
import com.movchan.jdbc.domain.DeveloperSkillKey;
import com.movchan.jdbc.error.EntityNotFoundException;
import com.movchan.jdbc.service.dto.DeveloperDTO;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperDAO developerDAO;
    private final DeveloperSkillService developerSkillService;
    private final DeveloperProjectService developerProjectService;

    public List<Developer> getAllDevelopers() {
        return developerDAO.getAll();
    }

    public Developer getDeveloperById(Long id) {
        return developerDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find developer with id = " + id));
    }

    public void deleteDeveloperById(Long id) {
        developerDAO.deleteById(id);
        deleteSkillForDeveloper(id);
        deleteProjectForDeveloper(id);
    }

    public void createDeveloper(DeveloperDTO developerDTO) {
        Developer developer = new Developer();
        developer.setName(developerDTO.getName());
        developer.setAge(developerDTO.getAge());
        developer.setSex(developerDTO.getSex());
        developer.setSalary(developerDTO.getSalary());
        Long id = developerDAO.create(developer).orElseThrow(() -> new EntityNotFoundException("Cannot create new developer"));
        List<Long> skills = developerDTO.getSkills();
        if(!skills.isEmpty()) {
            for (Long skillId : skills) {
                DeveloperSkillKey developerSkillKey = new DeveloperSkillKey(id, skillId);
                createSkillForDeveloper(developerSkillKey);
            }
        }
        List<Long> projects = developerDTO.getProjects();
        if(!projects.isEmpty()) {
            for (Long projectId : projects) {
                DeveloperProjectKey developerProjectKey = new DeveloperProjectKey(id, projectId);
                createProjectForDeveloper(developerProjectKey);
            }
        }
    }

    public void updateDeveloper(DeveloperDTO developerDTO) {
        Developer developer = new Developer();
        Long id = developerDTO.getId();
        developer.setId(id);
        developer.setName(developerDTO.getName());
        developer.setAge(developerDTO.getAge());
        developer.setSex(developerDTO.getSex());
        developer.setSalary(developerDTO.getSalary());
        developerDAO.update(developer);
        List<Long> skills = developerDTO.getSkills();
        List<DeveloperSkillKey> developerSkillKeys = new ArrayList<>();
        if(!skills.isEmpty()) {
            for (Long skillId : skills) {
                DeveloperSkillKey developerSkillKey = new DeveloperSkillKey(id, skillId);
                developerSkillKeys.add(developerSkillKey);
            }
            updateSkillsForDeveloper(developerSkillKeys);
        }
        List<Long> projects = developerDTO.getProjects();
        List<DeveloperProjectKey> developerProjectKeys = new ArrayList<>();
        if(!projects.isEmpty()) {
            for (Long projectId : projects) {
                DeveloperProjectKey developerProjectKey = new DeveloperProjectKey(id, projectId);
                developerProjectKeys.add(developerProjectKey);
            }
            updateProjectsForDeveloper(developerProjectKeys);
        }
    }

    private void createSkillForDeveloper(DeveloperSkillKey developerSkillKey) {
        developerSkillService.createDeveloperSkill(developerSkillKey);
    }

    private void deleteSkillForDeveloper(Long developerId) {
        developerSkillService.deleteDeveloperSkill(developerId);
    }

    private void updateSkillsForDeveloper(List<DeveloperSkillKey> developerSkillKeys) {
        developerSkillService.updateDeveloperSkill(developerSkillKeys);
    }

    private void createProjectForDeveloper(DeveloperProjectKey developerProjectKey) {
        developerProjectService.createDeveloperProject(developerProjectKey);
    }

    private void deleteProjectForDeveloper(Long developerId) {
        developerProjectService.deleteDeveloperProject(developerId);
    }

    private void updateProjectsForDeveloper(List<DeveloperProjectKey> developerProjectKeys) {
        developerProjectService.updateDeveloperProject(developerProjectKeys);
    }

    public List<Developer> getJavaDevelopers() {
        return developerDAO.getJavaDevelopers();
    }

    public List<Developer> getMiddleDevelopers() {
        return developerDAO.getMiddleDevelopers();
    }
}
