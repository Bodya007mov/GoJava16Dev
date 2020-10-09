package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.DeveloperSkillDAO;
import com.movchan.jdbc.domain.DeveloperSkillKey;
import com.movchan.jdbc.error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeveloperSkillService {

    private final DeveloperSkillDAO developerSkillDAO;

    public List<DeveloperSkillKey> getAllDeveloperSkillKeys() {
        return developerSkillDAO.getAll();
    }

    public DeveloperSkillKey getDeveloperSkillKeyByDeveloperId(Long id) {
        return developerSkillDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find developerSkillKey with developerId = " + id));
    }

    public void updateDeveloperSkill(List<DeveloperSkillKey> developerSkillKeys) {
        developerSkillDAO.deleteById(developerSkillKeys.get(0).getDeveloperId());
        for (DeveloperSkillKey developerSkillKey : developerSkillKeys) {
            developerSkillDAO.create(developerSkillKey);
        }
    }

    public void createDeveloperSkill(DeveloperSkillKey developerSkillKey) {
        developerSkillDAO.create(developerSkillKey);
    }

    public void deleteDeveloperSkill(Long developerId) {
        developerSkillDAO.deleteById(developerId);
    }
}
