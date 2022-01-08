package com.foirfoot.dao;

import com.foirfoot.model.result.Result;
import com.foirfoot.model.team.Team;
import com.foirfoot.utils.MySQLConnection;
import exceptions.TeamNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResultDAOMySQL implements DAO<Result>{
    @Override
    public Optional<Result> get(int id) {
        return Optional.empty();
    }

    public List<Result> getAllResultsOfTeam(long id) {
        List<Result> results = new ArrayList<>();
        try {
            String query = "SELECT * FROM RESULTS WHERE home_team = " + id + " OR outside_team = "+ id +";";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TeamDAOMySQL teamDAOMySQL = new TeamDAOMySQL();
                Optional<Team> home = teamDAOMySQL.get(rs.getInt("home_team"));
                Optional<Team> outside = teamDAOMySQL.get(rs.getInt("outside_team"));
                Team homeTeam = null;
                Team outsideTeam = null;
                homeTeam = home.orElseThrow(TeamNotFoundException::new);
                outsideTeam = outside.orElseThrow(TeamNotFoundException::new);
                results.add(new Result(homeTeam, outsideTeam, rs.getInt("score_ht"), rs.getInt("score_ot")));
            }
        } catch (SQLException | TeamNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Optional<Result>> getAll() {
        return null;
    }

    @Override
    public void save(Result result) throws SQLIntegrityConstraintViolationException {
        try {
            String query = "INSERT INTO RESULTS (score_ht, score_ot, home_team, outside_team) " +
                    "VALUES ('" + result.getScoreHT() + "', " + result.getScoreOT() + ", '" + result.getHomeTeam().getId() + "', '" + result.getOutsideTeam().getId() + "');";
            PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    result.setId(generatedKeys.getInt(1));
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
    public void update(Result result) {

    }

    @Override
    public void delete(Result result) {

    }
}
