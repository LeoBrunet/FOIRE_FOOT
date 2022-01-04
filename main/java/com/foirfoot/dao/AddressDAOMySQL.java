package com.foirfoot.dao;

import com.foirfoot.model.shop.Address;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.Transaction;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class AddressDAOMySQL implements DAO<Address>{
    public AddressDAOMySQL(){
    }

    @Override
    public Optional<Address> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Address>> getAll() {
        return null;
    }

    @Override
    public void save(Address address) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void update(Address address, String[] params) {

    }

    @Override
    public void delete(Address address) {

    }



}
