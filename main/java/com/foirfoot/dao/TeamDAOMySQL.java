package com.foirfoot.dao;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import com.foirfoot.utils.MySQLConnection;
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
                Club club = clubDAOMySQL.get(id).orElseThrow(UserNotFoundException::new);

                team = new Team(rs.getInt("team_id"),rs.getString("team_name"),club,rs.getString("category"),rs.getString("type"));
            }
        } catch (SQLException | UserNotFoundException e) {
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
                teams.add(new Team(rs.getString("team_name")));
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
