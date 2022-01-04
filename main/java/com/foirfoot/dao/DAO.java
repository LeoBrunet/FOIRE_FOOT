package com.foirfoot.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(int id);

    List<Optional<T>> getAll();

    void save(T t) throws SQLIntegrityConstraintViolationException;

    void update(T t);

    void delete(T t);
}