package com.movchan.hibernate.service;

import com.movchan.hibernate.dao.LanguageDAO;
import com.movchan.hibernate.domain.Language;
import com.movchan.hibernate.service.dto.LanguageDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public class LanguageService {

    private final LanguageDAO languageDAO;

    public List<Language> getAllLanguages() {
        return languageDAO.getAll();
    }

    public Language getLanguageById(Long id) {
        return languageDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find language with id = " + id));
    }

    public void deleteLanguageById(Long id) {
        languageDAO.deleteById(id);
    }

    public void createLanguage(LanguageDTO languageDTO) {
        Language language = new Language();
        language.setName(languageDTO.getName());
        languageDAO.create(language);
    }

    public void updateLanguage(LanguageDTO languageDTO) {
        Language language = new Language();
        Long id = languageDTO.getId();
        language.setId(id);
        language.setName(languageDTO.getName());
        languageDAO.update(language, id);
    }
}
