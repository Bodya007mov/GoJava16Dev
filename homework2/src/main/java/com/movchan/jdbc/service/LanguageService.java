package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.LanguageDAO;
import com.movchan.jdbc.domain.Language;
import com.movchan.jdbc.error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

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

    public void createLanguage(Language language) {
        languageDAO.create(language);
    }

    public void updateLanguage(Language language) {
        languageDAO.update(language);
    }
}
