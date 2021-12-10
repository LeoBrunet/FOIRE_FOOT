package com.foirfoot.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.foirfoot.model.User;
import com.foirfoot.utils.MySQLConnection;
import org.apache.commons.codec.digest.DigestUtils;

public class UserDAOMySQL implements DAO<User>{

    //private List<User> users = new ArrayList<>();

    //static Connection con = MySQLConnection.getConnection();

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    public Optional<User> getUserByEmail(String email){
        User user = null;
        try {
            String query = "SELECT * FROM USERS WHERE USERS.EMAIL = '" + email +"';";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll (){
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM USERS";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                users.add(new User(id, email, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "INSERT INTO USERS (email, password, first_name, name) VALUES ('" +
                    user.getEmail()+ "','" +
                    DigestUtils.sha1Hex(user.getPassword()) +"','"+
                    user.getFirstName()+"','" +
                    user.getName() + "')";
            System.out.println(query);
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
            throw sqlIntegrityConstraintViolationException;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, String[] params) {
        try {
            String query = "INSERT INTO USERS (email, password) VALUES ('" +user.getEmail()+ "','" + DigestUtils.sha1Hex(user.getPassword()) +"')";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {

    }
}
