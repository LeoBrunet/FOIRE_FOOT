package com.foirfoot.dao;

import com.foirfoot.model.shop.Product;
import com.foirfoot.utils.MySQLConnection;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOMySQL implements DAO<Product>{
    public ProductDAOMySQL(){
    }

    @Override
    public Optional<Product> get(int id) {
        Product product = null;
        try {
            String query = "SELECT * FROM PRODUCT WHERE PRODUCT.product_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDAOMySQL productDAOMySQL = new ProductDAOMySQL();



                Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
                InputStream is = sardine.get("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + rs.getString("product_image"));

                product = new Product(rs.getString("product_name"), rs.getString("product_category"), rs.getString("product_description"), rs.getString("product_price"),  rs.getString("product_stock"), rs.getString("product_image"));


            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(product);
    }


    @Override
    public List<Optional<Product>> getAll() {
        List<Optional<Product>> products = new ArrayList<>();
        return products;
    }

    @Override
    public void save(Product product) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "INSERT INTO PRODUCT (product_name, product_category,product_description,product_price,product_stock,product_image club_address) " +
                    "VALUES ('" + product.getName() + "', " + product.getNameCategory() + ", '" + product.getDescription()+ "', '" + product.getPrice() + "', " +
                    "'" + product.getStock() + "', '" + product.getImage() + "');";
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
    public void update(Product product, String[] params) {
        try {
            String query = "UPDATE PRODUCT SET product_id = '"+product.getId()+"', product_category = '" +product.getNameCategory()+ "', product_description = '"+product.getDescription()+"', product_price = '"+product.getPrice()+" ', product_stock = '"+product.getStock()+"', product_image = '"+product.getImage()+"' WHERE product_id = "+product.getId()+"";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Product product) {

    }
}
