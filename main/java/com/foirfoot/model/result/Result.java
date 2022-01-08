package com.foirfoot.model.result;

import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.Player;

import java.util.List;

public class Result {
    private int id;
    private boolean isHomeTeam;
    private Team homeTeam;
    private Team outsideTeam;
    private int scoreHT;
    private int scoreOT;
    private List<Player> scorersHT;
    private List<Player> scorersOT;

    public Result(int id, boolean isHomeTeam, Team homeTeam, Team outsideTeam, int scoreHT, int scoreOT, List<Player> scorersHT, List<Player> scorersOT){
        this.id = id;
        this.isHomeTeam = isHomeTeam;
        this.homeTeam = homeTeam;
        this.outsideTeam = outsideTeam;
        this.scoreHT = scoreHT;
        this.scoreOT = scoreOT;
        this.scorersHT = scorersHT;
        this.scorersOT = scorersOT;
    }

    public Result(Team home_team, Team outside_team, int score_ht, int score_ot) {
        this.homeTeam = home_team;
        this.outsideTeam = outside_team;
        this.scoreHT = score_ht;
        this.scoreOT = score_ot;
    }

    public int getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getOutsideTeam() {
        return outsideTeam;
    }

    public int getScoreHT() {
        return scoreHT;
    }

    public int getScoreOT() {
        return scoreOT;
    }

    public List<Player> getScorersHT() {
        return scorersHT;
    }

    public List<Player> getScorersOT() {
        return scorersOT;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", HomeTeam='" + homeTeam + '\'' +
                ", OutsideTeam=" + outsideTeam +
                ", scoreHT='" + scoreHT + '\'' +
                ", scoreOT='" + scoreOT + '\'' +
                '}';
    }
}
