package com.foirfoot.dao;

public class MySQLDAOFactory implements AbstractDAOFactory<DAO<?>>{
    @Override
    public DAO<?> create(String mySQLDAOType) {
        if ("User".equalsIgnoreCase(mySQLDAOType)) {
            return new UserDAOMySQL();
        }else if ("Club".equalsIgnoreCase(mySQLDAOType)) {
            return new ClubDAOMySQL();
        }else if ("Product".equalsIgnoreCase(mySQLDAOType)) {
            return new ProductDAOMySQL();
        }else if ("Basket".equalsIgnoreCase(mySQLDAOType)) {
            return new ProductDAOMySQL();
        }else if ("Transaction".equalsIgnoreCase(mySQLDAOType)) {
            return new ProductDAOMySQL();
        }else if ("Address".equalsIgnoreCase(mySQLDAOType)) {
            return new AddressDAOMySQL();
        }else if ("Team".equalsIgnoreCase(mySQLDAOType)) {
            return new TeamDAOMySQL();
        }else if ("Category".equalsIgnoreCase(mySQLDAOType)) {
            return new CategoryDAOMySQL();
        }else if ("Type".equalsIgnoreCase(mySQLDAOType)) {
            return new TypeDAOMySQL();
        }
        return null;
    }
}
