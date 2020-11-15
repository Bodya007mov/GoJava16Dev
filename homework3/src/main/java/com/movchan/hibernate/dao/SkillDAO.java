package com.movchan.hibernate.dao;

import com.movchan.hibernate.domain.Skill;

public class SkillDAO extends GenericDAO<Skill, Long> {

    @Override
    public Class<Skill> getEntityClass() {
        return Skill.class;
    }
}
