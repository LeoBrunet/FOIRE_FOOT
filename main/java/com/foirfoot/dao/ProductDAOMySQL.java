package com.foirfoot.dao;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Product;
import com.foirfoot.model.team.Team;
import com.foirfoot.utils.MySQLConnection;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import exceptions.ProductNotFoundException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOMySQL implements DAO<Product>{

    public ProductDAOMySQL() {
    }

    @Override
    public Optional<Product> get(int id) {
        Product product = null;
        try {
            String query = "SELECT * FROM PRODUCT WHERE PRODUCT.product_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
                InputStream is = sardine.get("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + rs.getString("product_image"));

                product = new Product(rs.getString("product_name"), rs.getString("product_category"), rs.getString("product_description"), rs.getInt("product_price"),  rs.getString("product_stock"), rs.getInt("product_clubId"), rs.getString("product_image"), is);
            }
        }  catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(product);
    }


    @Override
    public List<Optional<Product>> getAll() {
        List<Optional<Product>> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM PRODUCT ";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                products.add(Optional.of(new Product(rs.getInt("product_id"),rs.getString("product_name"),rs.getString("product_category"),rs.getString("product_description"),rs.getInt("product_price"),rs.getString("product_stock"),rs.getInt("product_clubId"), rs.getString("product_image"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Optional<Product>> getAllProductsOfClub(int clubId) {
        List<Optional<Product>> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM PRODUCT WHERE PRODUCT.product_clubId = " + clubId + ";";

            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
                InputStream is = sardine.get("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + rs.getString("product_image"));
                products.add(Optional.of(new Product(rs.getInt("product_id"),rs.getString("product_name"),rs.getString("product_category"),rs.getString("product_description"),rs.getInt("product_price"),rs.getString("product_stock"),rs.getInt("product_clubId"), rs.getString("product_image"), is)));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product product) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "INSERT INTO PRODUCT (product_name, product_category,product_description,product_price,product_stock,product_clubId,product_image) " +
                    "VALUES ('" + product.getName() + "', '"  + product.getNameCategory() + "', '"+ product.getDescription()+ "', '" + product.getPrice() + "', " +
                    "'" + product.getStock() +"', '" + product.getClubId() +"', '" + product.getImageName()  + "');";
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

    public void save(Product product, String localPathToImage) throws SQLIntegrityConstraintViolationException {
        save(product);
        try {
            Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
            byte[] data = FileUtils.readFileToByteArray(new File(localPathToImage));
            sardine.put("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + product.getImageName(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            String query = "UPDATE PRODUCT SET product_id = '"+product.getId()+"', product_description = '"+product.getNameCategory()+"', product_description = '"+product.getDescription()+"', product_price = '"+product.getPrice()+" ', product_stock = '"+product.getStock()+"', product_clubId = '"+product.getClubId()+"', product_image = '"+product.getImageName()+"' WHERE product_id = "+product.getId()+"";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Optional<Product>> products = getAll();
        List<Product> returnedProducts = new ArrayList<>();
        for (Optional<Product> product : products) {
            returnedProducts.add(product.orElseThrow(ProductNotFoundException::new));
        }
        return returnedProducts;
    }

    @Override
    public void delete(Product product) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "DELETE FROM PRODUCT WHERE product_id = "+product.getId()+"";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();


        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            throw sqlIntegrityConstraintViolationException;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
