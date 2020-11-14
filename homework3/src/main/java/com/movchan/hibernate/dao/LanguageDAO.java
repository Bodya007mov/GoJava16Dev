package com.movchan.hibernate.dao;

import com.movchan.hibernate.domain.Language;

public class LanguageDAO extends GenericDAO<Language, Long> {

    @Override
    public Class<Language> getEntityClass() {
        return Language.class;
    }
}
