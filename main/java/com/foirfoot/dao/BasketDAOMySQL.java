package com.foirfoot.dao;

import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.Product;
import com.foirfoot.utils.MySQLConnection;
import com.foirfoot.view.Main;

import java.sql.*;
import java.util.ArrayList;
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





    public Basket getBasketOfUser(int user_id) {
        List<Product> products = new ArrayList<>();
        int total = 0;

        try {

                String query = "SELECT * FROM PRODUCT INNER JOIN BASKET ON PRODUCT.product_id = BASKET.product_id WHERE  user_id = " + user_id + ";";//ou id

                PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getInt("product_price"), rs.getString("product_stock"), rs.getInt("product_clubId"));
                    products.add(product);
                    //total+= product.getPrice();
                }


            } catch(SQLException e){
                e.printStackTrace();
            }

        Basket basket = new Basket(user_id,products);

        return basket;
    }
}