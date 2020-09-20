package com.movchan.jdbc.dao;

import com.movchan.jdbc.domain.Skill;

import java.util.List;
import java.util.Optional;

public class SkillDAO extends GenericDAO<Skill, Long> {

    @Override
    List<Skill> getAll() {
        return null;
    }

    @Override
    Optional<Skill> getById(Long id) {
        return Optional.empty();
    }

    @Override
    void deleteById(Long id) {

    }

    @Override
    Optional<Long> create(Skill skill) {
        return Optional.empty();
    }

    @Override
    void update(Skill skill) {

    }
}
