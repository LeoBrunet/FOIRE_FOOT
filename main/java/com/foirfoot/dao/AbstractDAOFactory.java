package com.foirfoot.dao;

import exceptions.ProductNotFoundException;

public interface AbstractDAOFactory<T> {
    public T create(String mySQLDAOType) throws ProductNotFoundException;
}
