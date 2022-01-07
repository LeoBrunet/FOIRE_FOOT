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
            String query = "INSERT INTO TRANSACTION (transaction_user, transaction_basket,transaction_address,transaction_city,transaction_country,transaction_payment) " +
                    "VALUES ('" + transaction.getUserId() + "', '" + transaction.getBasketId()+ "', '" + transaction.getaddress() + "', " +
                    "'" + transaction.getCity() +"', '" + transaction.getCountry() +"', '" + transaction.getPayment()  + "');";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setId(generatedKeys.getInt(1));
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
    public void update(Transaction transaction) {

    }


    @Override
    public void delete(Transaction transaction) {

    }
}
