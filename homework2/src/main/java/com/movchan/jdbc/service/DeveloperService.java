package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.DeveloperDAO;
import com.movchan.jdbc.domain.Developer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperDAO developerDAO;
    private final DeveloperSkillService developerSkillService;

    public List<Developer> getAllDevelopers() {
        return developerDAO.getAll();
    }

    public Developer getDeveloperById(Long id) {
        return developerDAO.getById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteDeveloperById(Long id) {
        developerDAO.deleteById(id);
        deleteSkillForDeveloper(id);
    }

    public void createDeveloper(DeveloperDTO developerDTO) {
        Developer developer = new Developer();
        developer.setName(developerDTO.getName());
        developer.setAge(developerDTO.getAge());
        developer.setSex(developerDTO.getSex());
        developer.setSalary(developerDTO.getSalary());
        Long id = developerDAO.create(developer).orElseThrow(RuntimeException::new);
        List<Long> skills = developerDTO.getSkills();
        if(!skills.isEmpty()) {
            for (Long skillId : skills) {
                createSkillForDeveloper(id, skillId);
            }
        }
    }

    public void updateDeveloper(Developer developer) {
        developerDAO.update(developer);
    }

    private void createSkillForDeveloper(Long developerId, Long skillId) {
        developerSkillService.createDeveloperSkill(developerId, skillId);
    }

    private void deleteSkillForDeveloper(Long developerId) {
        developerSkillService.deleteDeveloperSkill(developerId);
    }
}
