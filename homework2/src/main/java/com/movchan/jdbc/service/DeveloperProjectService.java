package com.movchan.jdbc.service;


import com.movchan.jdbc.dao.DeveloperProjectDAO;
import com.movchan.jdbc.domain.DeveloperProjectKey;
import com.movchan.jdbc.error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeveloperProjectService {

    private final DeveloperProjectDAO developerProjectDAO;

    public List<DeveloperProjectKey> getAllDeveloperProjectKeys() {
        return developerProjectDAO.getAll();
    }

    public DeveloperProjectKey getDeveloperProjectKeyByDeveloperId(Long id) {
        return developerProjectDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find developerProjectKey with developerId = " + id));
    }

    public void updateDeveloperProject(List<DeveloperProjectKey> developerProjectKeys) {
        developerProjectDAO.deleteById(developerProjectKeys.get(0).getDeveloperId());
        for (DeveloperProjectKey developerProjectKey : developerProjectKeys) {
            developerProjectDAO.create(developerProjectKey);
        }
    }

    public void createDeveloperProject(DeveloperProjectKey developerProjectKey) {
        developerProjectDAO.create(developerProjectKey);
    }

    public void deleteDeveloperProject(Long developerId) {
        developerProjectDAO.deleteById(developerId);
    }
}
