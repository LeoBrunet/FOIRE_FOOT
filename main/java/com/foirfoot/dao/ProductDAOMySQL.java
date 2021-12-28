package com.foirfoot.dao;

import com.foirfoot.model.shop.Product;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class ProductDAOMySQL implements DAO<Product>{
    public ProductDAOMySQL(){
    }

    @Override
    public Optional<Product> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Product>> getAll() {
        return null;
    }

    @Override
    public void save(Product product) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {

    }
}
