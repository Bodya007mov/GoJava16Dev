package com.movchan.hibernate.service;

import com.movchan.hibernate.dao.SkillDAO;
import com.movchan.hibernate.domain.Language;
import com.movchan.hibernate.domain.Skill;
import com.movchan.hibernate.service.dto.SkillDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public class SkillService {

    private final SkillDAO skillDAO;
    private final LanguageService languageService;

    public List<Skill> getAllSkills() {
        return skillDAO.getAll();
    }

    public Skill getSkillById(Long id) {
        return skillDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find skill with id = " + id));
    }

    public void deleteSkillById(Long id) {
        skillDAO.deleteById(id);
    }

    public void createSkill(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setLevel(skillDTO.getLevel());
        Long languageId = skillDTO.getLanguageId();
        Language language = languageService.getLanguageById(languageId);
        skill.setLanguage(language);
        skillDAO.create(skill);
    }

    public void updateSkill(SkillDTO skillDTO) {
        Skill skill = new Skill();
        Long id = skillDTO.getId();
        skill.setId(id);
        skill.setLevel(skillDTO.getLevel());
        Long languageId = skillDTO.getLanguageId();
        Language language = languageService.getLanguageById(languageId);
        skill.setLanguage(language);
        skillDAO.update(skill, id);
    }
}
