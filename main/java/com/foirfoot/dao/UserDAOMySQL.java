package com.foirfoot.dao;

import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import com.foirfoot.utils.MySQLConnection;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOMySQL implements DAO<User> {

    private User createUser(ResultSet rs) {
        User user = null;
        try {
            boolean isClubCreator = rs.getInt("user_id") == rs.getInt("creator_user_id");
            user = new User(rs.getInt("user_id"), rs.getString("user_email"), rs.getString("user_password"), rs.getString("user_name"), rs.getString("user_first_name"), RoleName.values()[rs.getInt("user_role")], rs.getInt("club_id"), rs.getInt("team_id"), isClubCreator, new Basket());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Optional<User> get(int id) {
        User user = null;
        try {
            String query = "SELECT * FROM USERS LEFT JOIN CLUBS ON USERS.club_id = CLUBS.creator_user_id LEFT JOIN TEAMS ON USERS.team_id = TEAMS.team_id WHERE USERS.user_id = '" + id + "';";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> getUserByEmail(String email) {
        User user = null;
        try {
            String query = "SELECT * FROM USERS LEFT JOIN CLUBS ON USERS.club_id = CLUBS.club_id LEFT JOIN TEAMS ON USERS.team_id = TEAMS.team_id WHERE USERS.user_email = '" + email + "';";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<Optional<User>> getAll() {
        List<Optional<User>> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM USERS LEFT JOIN CLUBS ON USERS.club_id = CLUBS.creator_user_id LEFT JOIN TEAMS ON USERS.team_id = TEAMS.team_id";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(Optional.ofNullable(createUser(rs)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getAllUsersOfClubWithRole(long clubId, RoleName roleName) {
        List<User> players = new ArrayList<>();
        try {
            String query = "SELECT * FROM USERS INNER JOIN CLUBS C on USERS.club_id = C.club_id WHERE USERS.club_id = " + clubId + " AND user_role = " + roleName.ordinal() + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                players.add(createUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<User> searchUsers(String nameSearched) {
        List<User> players = new ArrayList<>();
        try {
            String query = "SELECT * FROM USERS INNER JOIN CLUBS C on USERS.club_id = C.club_id WHERE LOWER(USERS.user_first_name) LIKE '%" + nameSearched + "%' OR LOWER(USERS.user_name) LIKE '%" + nameSearched + "%';";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = createUser(rs);
                if (user.getRoleName() == RoleName.coach || user.getRoleName() == RoleName.player) {
                    players.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<User> getAllUsersOfTeamWithRole(long teamId, RoleName roleName) {
        List<User> players = new ArrayList<>();
        try {
            String query = "SELECT * FROM USERS INNER JOIN TEAMS T on USERS.team_id = T.team_id INNER JOIN CLUBS C on USERS.club_id = C.club_id WHERE USERS.team_id = "+teamId+" AND user_role = "+roleName.ordinal()+";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                boolean isClubCreator = rs.getInt("user_id") == rs.getInt("creator_user_id");
                players.add(new User(rs.getInt("user_id"), rs.getString("user_email"), rs.getString("user_password"),rs.getString("user_name"), rs.getString("user_first_name"), RoleName.values()[rs.getInt("user_role")], rs.getInt("club_id"), (int)teamId, isClubCreator, new Basket()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public void save(User user) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "INSERT INTO USERS (user_email, user_password, user_first_name, user_name) VALUES ('" +
                    user.getEmail() + "','" +
                    DigestUtils.sha1Hex(user.getPassword()) + "','" +
                    user.getFirstName() + "','" +
                    user.getName() + "')";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            throw sqlIntegrityConstraintViolationException;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            String query = "UPDATE USERS SET club_id = '" + user.getClub().getId() + "', user_email = '" + user.getEmail() + "', user_first_name = '" + user.getFirstName() + "', user_name = '" + user.getName() + "' WHERE user_id = " + user.getId() + "";
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
