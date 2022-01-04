package com.foirfoot.dao;

import com.foirfoot.model.shop.Product;
import com.foirfoot.model.shop.Transaction;
import com.foirfoot.utils.MySQLConnection;

import java.sql.*;
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
        try {
            String query = "INSERT INTO TRANSACTION (product_name, product_description,product_price,product_stock) " +
                    "VALUES ('" + product.getName() + "', '" + product.getDescription()+ "', '" + product.getPrice() + "', " +
                    "'" + product.getStock()  + "');";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            throw sqlIntegrityConstraintViolationException;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Transaction transaction, String[] params) {

    }

    @Override
    public void delete(Transaction transaction) {

    }
}
