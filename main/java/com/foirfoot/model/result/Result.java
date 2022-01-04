package com.foirfoot.model.result;

import com.foirfoot.model.user.Player;

import java.util.List;

public class Result {
    private boolean isHomeTeam;
    private String homeTeam;
    private String outsideTeam;
    private int scoreHT;
    private int scoreOT;
    private List<Player> scorersHT;
    private List<Player> scorersOT;

    public Result(boolean isHomeTeam, String homeTeam, String outsideTeam, int scoreHT, int scoreOT, List<Player> scorersHT, List<Player> scorersOT){
        this.isHomeTeam = isHomeTeam;
        this.homeTeam = homeTeam;
        this.outsideTeam = outsideTeam;
        this.scoreHT = scoreHT;
        this.scoreOT = scoreOT;
        this.scorersHT = scorersHT;
        this.scorersOT = scorersOT;
    }

    public Result(String home_team, String outside_team, int score_ht, int score_ot) {
        this.homeTeam = home_team;
        this.outsideTeam = outside_team;
        this.scoreHT = score_ht;
        this.scoreOT = score_ot;
    }
}
