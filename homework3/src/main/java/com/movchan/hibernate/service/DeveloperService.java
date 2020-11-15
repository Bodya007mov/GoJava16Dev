package com.movchan.hibernate.service;

import com.movchan.hibernate.dao.*;
import com.movchan.hibernate.domain.*;
import com.movchan.hibernate.service.dto.DeveloperDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperDAO developerDAO;
    private final ServiceFactory serviceFactory;

    public List<Developer> getAllDevelopers() {
        return developerDAO.getAll();
    }

    public Developer getDeveloperById(Long id) {
        return developerDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find developer with id = " + id));
    }

    public void deleteDeveloperById(Long id) {
        developerDAO.deleteById(id);
    }

    public void createDeveloper(DeveloperDTO developerDTO) {
        Developer developer = new Developer();
        developer.setName(developerDTO.getName());
        developer.setAge(developerDTO.getAge());
        developer.setSex(developerDTO.getSex());
        developer.setSalary(developerDTO.getSalary());
        List<Long> skillIds = developerDTO.getSkills();
        for (Long skillId : skillIds) {
            SkillService skillService = serviceFactory.getSkillService();
            Skill skill = skillService.getSkillById(skillId);
            developer.getSkills().add(skill);
        }
        List<Long> projectIds = developerDTO.getProjects();
        for (Long projectId : projectIds) {
            ProjectService projectService = serviceFactory.getProjectService();
            Project project = projectService.getProjectById(projectId);
            developer.getProjects().add(project);
        }
        developerDAO.create(developer).orElseThrow(() -> new EntityNotFoundException("Cannot create new developer"));
    }

    public void updateDeveloper(DeveloperDTO developerDTO) {
        Developer developer = new Developer();
        Long id = developerDTO.getId();
        developer.setId(id);
        developer.setName(developerDTO.getName());
        developer.setAge(developerDTO.getAge());
        developer.setSex(developerDTO.getSex());
        developer.setSalary(developerDTO.getSalary());
        List<Skill> skills = developer.getSkills();
        skills.clear();
        List<Long> skillIds = developerDTO.getSkills();
        for (Long skillId : skillIds) {
            SkillService skillService = serviceFactory.getSkillService();
            Skill skill = skillService.getSkillById(skillId);
            skills.add(skill);
        }
        List<Project> projects = developer.getProjects();
        projects.clear();
        List<Long> projectIds = developerDTO.getProjects();
        for (Long projectId : projectIds) {
            ProjectService projectService = serviceFactory.getProjectService();
            Project project = projectService.getProjectById(projectId);
            projects.add(project);
        }
        developerDAO.update(developer, id);
    }
}
