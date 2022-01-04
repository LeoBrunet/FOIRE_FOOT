package com.foirfoot.dao;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import com.foirfoot.utils.MySQLConnection;
import exceptions.ClubNotFoundException;
import exceptions.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamDAOMySQL implements DAO<Team>{

    @Override
    public Optional<Team> get(int id) {
        Team team = null;
        try {
            String query = "SELECT * FROM TEAMS WHERE TEAMS.team_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClubDAOMySQL clubDAOMySQL = new ClubDAOMySQL();
                UserDAOMySQL userDAOMySQL = new UserDAOMySQL();
                List<User> players = userDAOMySQL.getAllUsersOfTeamWithRole(id, RoleName.player);
                List<User> coachs = userDAOMySQL.getAllUsersOfTeamWithRole(id, RoleName.coach);
                System.out.print(players);
                team = new Team(rs.getInt("team_id"),rs.getString("team_name"),null,rs.getString("category"),rs.getString("type"), players, coachs);
                System.out.println(team);
                Club club = clubDAOMySQL.get(id).orElseThrow(ClubNotFoundException::new);
                team.setClub(club);
                for (User p : players) {
                    p.setClub(club);
                }
                for (User c : coachs) {
                    c.setClub(club);
                }
                System.out.println(team);
                System.out.println("oui");
            }
        } catch (SQLException | ClubNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(team);
    }

    @Override
    public List<Optional<Team>> getAll() {
        return null;
    }

    public List<Team> getAllTeamsOfClub(long id) {
        List<Team> teams = new ArrayList<>();
        try {
            String query = "SELECT * FROM TEAMS WHERE club_id = " + id + ";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                teams.add(new Team(rs.getInt("team_id"), rs.getString("team_name"), null, rs.getString("category"), rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public void save(Team team) throws SQLIntegrityConstraintViolationException {
        System.out.println(team.getClub());
        try {
            String query = "INSERT INTO TEAMS (team_name, club_id, category, type) " +
                    "VALUES ('" + team.getName() + "', " + team.getClub().getId() + ", '" + team.getCategory() + "', '" + team.getType() + "');";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    team.setId(generatedKeys.getInt(1));
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
    public void update(Team team, String[] params) {


    }

    @Override
    public void delete(Team team) {

    }
}
