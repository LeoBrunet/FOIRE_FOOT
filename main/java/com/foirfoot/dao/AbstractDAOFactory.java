package com.foirfoot.dao;

public interface AbstractDAOFactory<T> {
    public T create(String mySQLDAOType);
}
