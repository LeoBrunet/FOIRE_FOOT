package com.foirfoot.dao;

public class MySQLDAOFactory implements AbstractDAOFactory<DAO>{
    @Override
    public DAO create(String mySQLDAOType) {
        if ("User".equalsIgnoreCase(mySQLDAOType)) {
            return new UserDAOMySQL();
        }
        return null;
    }
}
