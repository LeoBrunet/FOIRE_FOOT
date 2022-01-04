package com.foirfoot.dao;

import com.foirfoot.model.category.Category;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.utils.MySQLConnection;
import exceptions.UserNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOMySQL implements DAO<Category>{
    @Override
    public Optional<Category> get(int id) {
        Category category = null;
        try {
            String query = "SELECT * FROM CATEGORIES WHERE CATEGORIES.cat_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                category = new Category(rs.getInt("cat_id"),rs.getString("cat_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(category);
    }


    @Override
    public List<Optional<Category>> getAll() {
        return null;
    }

    public static List<String> getCategoryList(){
        List<String> categories = new ArrayList<>();
        try {
            String query = "SELECT * FROM CATEGORIES;";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = new Category(rs.getString("cat_name")).getName();
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void save(Category category) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }
}
