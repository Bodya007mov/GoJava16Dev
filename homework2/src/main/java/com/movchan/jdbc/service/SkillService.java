package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.SkillDAO;
import com.movchan.jdbc.domain.Skill;
import com.movchan.jdbc.error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SkillService {

    private final SkillDAO skillDAO;

    public List<Skill> getAllSkills() {
        return skillDAO.getAll();
    }

    public Skill getSkillById(Long id) {
        return skillDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find skill with id = " + id));
    }

    public void deleteSkillById(Long id) {
        skillDAO.deleteById(id);
    }

    public void createSkill(Skill skill) {
        skillDAO.create(skill);
    }

    public void updateSkill(Skill skill) {
        skillDAO.update(skill);
    }
}
