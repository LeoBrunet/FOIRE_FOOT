package com.foirfoot.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.foirfoot.model.User;
import com.foirfoot.utils.DatabaseConnection;

public class UserDAOMySQL implements DAO{

    private List<User> users = new ArrayList<>();

    static Connection con = DatabaseConnection.getConnection();

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    public Optional<User> getUserByEmail(String email){
        User user = null;
        try {
            String query = "select * from users where users.email = '" + email +"';";
            PreparedStatement ps = con.prepareStatement(query);
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
            String query = "select * from users";
            PreparedStatement ps = con.prepareStatement(query);
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
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
