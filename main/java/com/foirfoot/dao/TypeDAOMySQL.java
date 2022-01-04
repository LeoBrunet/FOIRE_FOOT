package com.foirfoot.dao;

import com.foirfoot.model.category.Category;
import com.foirfoot.model.type.Type;
import com.foirfoot.utils.MySQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeDAOMySQL implements DAO<Type>{
    @Override
    public Optional<Type> get(int id) {
        Type type = null;
        try {
            String query = "SELECT * FROM TYPE WHERE TYPE.type_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                type = new Type(rs.getInt("type_id"),rs.getString("type_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(type);
    }


    @Override
    public List<Optional<Type>> getAll() {
        return null;
    }

    public static List<String> getTypeList(){
        List<String> types = new ArrayList<>();
        try {
            String query = "SELECT * FROM TYPES;";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                types.add(new Type(rs.getString("type_name")).getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public void save(Type type) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void update(Type type, String[] params) {

    }

    @Override
    public void delete(Type type) {

    }
}
