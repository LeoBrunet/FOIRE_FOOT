package com.foirfoot.dao;

import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.Product;
import com.foirfoot.utils.MySQLConnection;
import com.foirfoot.view.Main;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BasketDAOMySQL implements DAO<Basket>{
    public BasketDAOMySQL(){
    }

    @Override
    public Optional<Basket> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Basket>> getAll() {
        return null;
    }

    @Override
    public void save(Basket basket) throws SQLIntegrityConstraintViolationException {
        try {
            System.out.println(Main.connectedUser.getId());
            System.out.println(basket.getUser_id());
            String query = "INSERT INTO BASKET (user_id, product_id) " +
                    "VALUES ('" + Main.connectedUser.getId() + "', '" + basket.getProductId() + "');";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();


        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            throw sqlIntegrityConstraintViolationException;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Basket basket) {

    }

    @Override
    public void delete(Basket basket) {

    }
    public List<Product> SearchSameId(int user_id){
        return null;
    }
}