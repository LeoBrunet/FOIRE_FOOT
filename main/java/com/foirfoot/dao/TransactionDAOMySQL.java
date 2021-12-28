package com.foirfoot.dao;

import com.foirfoot.model.shop.Transaction;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class TransactionDAOMySQL implements DAO<Transaction>{
    @Override
    public Optional<Transaction> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Transaction>> getAll() {
        return null;
    }

    @Override
    public void save(Transaction transaction) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void update(Transaction transaction, String[] params) {

    }

    @Override
    public void delete(Transaction transaction) {

    }
}
