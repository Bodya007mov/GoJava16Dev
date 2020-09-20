package com.movchan.jdbc.dao;

import java.util.List;
import java.util.Optional;

abstract class GenericDAO<T, ID> {

    final String URL = "jdbc:postgresql://localhost:5432/homework";
    final String username = "postgres";
    final String password = "postgres007";

    abstract List<T> getAll();

    abstract Optional<T> getById(ID id);

    abstract void deleteById(ID id);

    abstract Optional<ID> create(T entity);

    abstract void update(T entity);
}
