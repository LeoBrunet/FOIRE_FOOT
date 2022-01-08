package com.foirfoot.dao;

import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.Product;
import com.foirfoot.model.shop.Transaction;
import com.foirfoot.utils.MySQLConnection;
import com.foirfoot.view.Main;

import java.sql.*;
import java.util.ArrayList;
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
            String query = "INSERT INTO TRANSACTION (transaction_user, transaction_basket,transaction_address,transaction_city,transaction_country,transaction_payment,transaction_nbproducts,transaction_total) " +
                    "VALUES ('" + transaction.getUser() + "', '" + transaction.getBasketId()+ "', '" + transaction.getaddress() + "', " +
                    "'" + transaction.getCity() +"', '" + transaction.getCountry() +"', '" + transaction.getPayment() +"', '" + transaction.getBasket().getNbProducts() +"', '" + transaction.getBasket().calculTotal()  + "');";
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

    public List<Transaction> getAllTransactionsPerUser(int user_id){
        List<Transaction> transactions = new ArrayList<>();

        try {

            String query = "SELECT * FROM TRANSACTION  WHERE  transaction_user= " + user_id + ";";

            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //probleme avec le basket
                Transaction transaction = new Transaction(rs.getInt("transaction_id"), rs.getInt("transaction_user"), Main.connectedUser.getBasket(), rs.getString("transaction_address"), rs.getString("transaction_city"), rs.getString("transaction_country"), rs.getString("transaction_payment"),rs.getInt("transaction_nbproducts"),rs.getInt("transaction_total"));
                transactions.add(transaction);
            }


        } catch(SQLException e){
            e.printStackTrace();
        }


        return transactions;
    }




}
