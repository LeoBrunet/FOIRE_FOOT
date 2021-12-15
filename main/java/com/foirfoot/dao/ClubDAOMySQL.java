package com.foirfoot.dao;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.*;
import com.foirfoot.utils.MySQLConnection;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import exceptions.UserNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClubDAOMySQL implements DAO<Club> {

    @Override
    public Optional<Club> get(int id) {
        Club club = null;
        try {
            String query = "SELECT * FROM CLUBS WHERE CLUBS.club_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDAOMySQL userDAOMySQL = new UserDAOMySQL();
                List<Team> teams = getAllTeamsOfClub(id);
                List<User> players = userDAOMySQL.getAllUsersOfClubWithRole(id, RoleName.player);
                List<User> coachs = userDAOMySQL.getAllUsersOfClubWithRole(id, RoleName.coach);
                User creator = userDAOMySQL.get(id).orElseThrow(UserNotFoundException::new);

                Sardine sardine = SardineFactory.begin("leo-ig", "ftyx-mloi-fhci");
                InputStream is = sardine.get("http://webdav-leo-ig.alwaysdata.net/foir_foot/images/" + rs.getString("club_image_name"));

                club = new Club(rs.getString("club_name"), rs.getString("club_address"), rs.getString("club_phone_number"), rs.getString("club_website"), creator, players, coachs, teams, is);

                for (User p : players) {
                    p.setClub(club);
                }
                for (User c : coachs) {
                    c.setClub(club);
                }
                club.getCreator().setClub(club);
            }
        } catch (SQLException | IOException | UserNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(club);
    }

    // TODO Passer dans le TeamDAOFactory
    private List<Team> getAllTeamsOfClub(long id) {
        List<Team> teams = new ArrayList<>();
        try {
            String query = "SELECT * FROM TEAMS WHERE club_id = "+id+";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                teams.add(new Team(rs.getString("team_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public List<Optional<Club>> getAll() {
        List<Optional<Club>> clubs = new ArrayList<>();
        return clubs;
    }

    @Override
    public void save(Club club) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void update(Club club, String[] params) {

    }

    @Override
    public void delete(Club club) {

    }
}
