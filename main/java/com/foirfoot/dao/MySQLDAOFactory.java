package com.foirfoot.dao;

public class MySQLDAOFactory implements AbstractDAOFactory<DAO<?>>{
    @Override
    public DAO<?> create(String mySQLDAOType) {
        if ("User".equalsIgnoreCase(mySQLDAOType)) {
            return new UserDAOMySQL();
        }else if ("Club".equalsIgnoreCase(mySQLDAOType)) {
            return new ClubDAOMySQL();
        }
        return null;
    }
}
