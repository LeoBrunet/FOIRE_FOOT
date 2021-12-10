package com.foirfoot.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t) throws SQLIntegrityConstraintViolationException;

    void update(T t, String[] params);

    void delete(T t);
}