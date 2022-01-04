package com.foirfoot.dao;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import com.foirfoot.utils.MySQLConnection;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import exceptions.UserNotFoundException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClubDAOMySQL implements DAO<Club> {

    private Club createClub(int id, ResultSet rs) {
        Club club = null;
        try {
            UserDAOMySQL userDAOMySQL = new UserDAOMySQL();
            TeamDAOMySQL teamDAOMySQL = new TeamDAOMySQL();
            List<Team> teams = teamDAOMySQL.getAllTeamsOfClub(id);
            List<User> players = userDAOMySQL.getAllUsersOfClubWithRole(id, RoleName.player);
            List<User> coachs = userDAOMySQL.getAllUsersOfClubWithRole(id, RoleName.coach);
            User creator = userDAOMySQL.get(rs.getInt("creator_user_id")).orElseThrow(UserNotFoundException::new);

            Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
            InputStream is = sardine.get("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + rs.getString("club_image_name"));

            club = new Club(rs.getInt("club_id"), rs.getString("club_name"), rs.getString("club_address"), rs.getString("club_phone_number"), rs.getString("club_website"), creator, players, coachs, teams, rs.getString("club_image_name"), is);

            for (User p : players) {
                p.setClub(club);
            }
            for (User c : coachs) {
                c.setClub(club);
            }
            for (Team t : teams) {
                t.setClub(club);
            }
            club.getCreator().setClub(club);
        } catch (UserNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return club;
    }

    @Override
    public Optional<Club> get(int id) {
        Club club = null;
        try {
            String query = "SELECT * FROM CLUBS WHERE CLUBS.club_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                club = createClub(id, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(club);
    }

    @Override
    public List<Optional<Club>> getAll() {
        List<Optional<Club>> clubs = new ArrayList<>();
        try {
            String query = "SELECT * FROM CLUBS;";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int club_id = rs.getInt("club_id");
                clubs.add(Optional.of(createClub(club_id, rs)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    public List<Club> searchClub(String clubName) {
        List<Club> clubs = new ArrayList<>();
        try {
            String query = "SELECT * FROM CLUBS WHERE LOWER(CLUBS.club_name) LIKE '%" + clubName.toLowerCase() + "%';";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int club_id = rs.getInt("club_id");
                clubs.add(createClub(club_id, rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    @Override
    public void save(Club club) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "INSERT INTO CLUBS (club_name, creator_user_id, club_address, club_phone_number, club_website, club_image_name) " +
                    "VALUES ('" + club.getName() + "', " + club.getCreator().getId() + ", '" + club.getAddress() + "', '" + club.getPhoneNumber() + "', " +
                    "'" + club.getWebsite() + "', '" + club.getImageName() + "');";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    club.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            throw sqlIntegrityConstraintViolationException;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Club club, String localPathToImage) throws SQLIntegrityConstraintViolationException {
        save(club);
        try {
            Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
            byte[] data = FileUtils.readFileToByteArray(new File(localPathToImage));
            sardine.put("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + club.getImageName(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Club club) {
        try {
            String query = "UPDATE CLUBS SET " +
                    "club_name = '" + club.getName() + "'" +
                    ", creator_user_id = " + club.getCreator().getId() +
                    ", club_address = '" + club.getAddress() + "'" +
                    ", club_phone_number = '" + club.getPhoneNumber() + "'" +
                    ", club_website = '" + club.getWebsite() + "'" +
                    ", club_image_name = '" + club.getImageName() + "' " +
                    "WHERE club_id = "+club.getId()+";";
            System.out.println(query);
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Club club, String localPathToImage) {
        update(club);
        try {
            Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
            byte[] data = FileUtils.readFileToByteArray(new File(localPathToImage));
            sardine.put("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + club.getImageName(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Club club) {

    }
}
