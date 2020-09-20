package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.DeveloperSkillDAO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeveloperSkillService {

    private final DeveloperSkillDAO developerSkillDAO;

    void createDeveloperSkill(Long developerId, Long skillId) {
        developerSkillDAO.create(developerId, skillId);
    }

    void deleteDeveloperSkill(Long developerId) {
        developerSkillDAO.delete(developerId);
    }
}
