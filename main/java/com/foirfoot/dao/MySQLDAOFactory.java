package com.foirfoot.dao;

public class MySQLDAOFactory implements AbstractDAOFactory<DAO<?>>{
    @Override
    public DAO<?> create(String mySQLDAOType) {
        if ("User".equalsIgnoreCase(mySQLDAOType)) {
            return new UserDAOMySQL();
        }else if ("Club".equalsIgnoreCase(mySQLDAOType)) {
            return new ClubDAOMySQL();
        }else if ("Team".equalsIgnoreCase(mySQLDAOType)) {
            return new TeamDAOMySQL();
        }else if ("Category".equalsIgnoreCase(mySQLDAOType)) {
            return new CategoryDAOMySQL();
        }else if ("Type".equalsIgnoreCase(mySQLDAOType)) {
            return new TypeDAOMySQL();
        }else if ("Result".equalsIgnoreCase(mySQLDAOType)) {
            return new ResultDAOMySQL();
        }
        return null;
    }
}
