package com.foirfoot.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
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
            String query = "SELECT * FROM USERS LEFT JOIN CLUBS ON USERS.club_id = CLUBS.creator_user_id LEFT JOIN TEAMS ON USERS.team_id = TEAMS.team_id WHERE USERS.user_email = '" + email +"';";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club club = new Club(rs.getString("club_name"));
                Team team = new Team(rs.getString("team_name"));
                boolean isClubCreator = rs.getInt("user_id") == rs.getInt("creator_user_id");
                user = new User(rs.getString("user_email"), rs.getString("user_password"),rs.getString("user_name"), rs.getString("user_first_name"), RoleName.values()[rs.getInt("user_role")], club, team, isClubCreator);
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
            String query = "SELECT * FROM USERS LEFT JOIN CLUBS ON USERS.club_id = CLUBS.creator_user_id LEFT JOIN TEAMS ON USERS.team_id = TEAMS.team_id";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club club = new Club(rs.getString("club_name"));
                Team team = new Team(rs.getString("team_name"));
                boolean isClubCreator = rs.getInt("user_id") == rs.getInt("creator_user_id");
                users.add(new User(rs.getString("user_email"), rs.getString("user_password"),rs.getString("user_name"), rs.getString("user_first_name"), RoleName.values()[rs.getInt("user_role")], club, team, isClubCreator));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "INSERT INTO USERS (user_email, user_password, user_first_name, user_name) VALUES ('" +
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
            String query = "INSERT INTO USERS (user_email, user_password, user_first_name, user_name) VALUES ('" +user.getEmail()+ "','" + DigestUtils.sha1Hex(user.getPassword()) +"','"+user.getFirstName()+"','"+user.getName()+"')";
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
