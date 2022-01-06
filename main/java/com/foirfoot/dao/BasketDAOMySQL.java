package com.foirfoot.dao;

import com.foirfoot.model.shop.Basket;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class BasketDAOMySQL implements DAO<Basket>{
    public BasketDAOMySQL(){
    }

    @Override
    public Optional<Basket> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Basket>> getAll() {
        return null;
    }

    @Override
    public void save(Basket basket) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void update(Basket basket) {

    }

    @Override
    public void delete(Basket basket) {

    }
}