package com.movchan.hibernate.dao;

import com.movchan.hibernate.domain.Project;

public class ProjectDAO extends GenericDAO<Project, Long> {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }
}
