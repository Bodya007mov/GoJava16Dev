package com.movchan.hibernate.dao;

import com.movchan.hibernate.domain.Developer;

public class DeveloperDAO extends GenericDAO<Developer, Long> {

    @Override
    public Class<Developer> getEntityClass() {
        return Developer.class;
    }
}
