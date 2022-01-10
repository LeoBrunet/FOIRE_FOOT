package com.foirfoot.model.team;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.result.Result;
import com.foirfoot.model.user.User;

import java.util.Comparator;
import java.util.List;

public class Team{
    private int id;
    private String name;
    private Club club;
    private String category;
    private String type;
    private List<User> players;
    private List<User> coachs;
    private List<Result> results;

    public Team(int id) {
        this.id = id;
    }

    public Team(int id, String name){
        this(id);
        this.name = name;
    }

    public Team(String category, String type){
        this.category = category;
        this.type = type;
    }

    public Team(String category, String type, Club club){
        this.category = category;
        this.type = type;
        this.club = club;
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(int id, String name, Club club, String category, String type) {
        this.id = id;
        this.name = name;
        this.club = club;
        this.category = category;
        this.type = type;
    }

    public Team(int id, String name, Club club, String category, String type, List<User> players, List<User> coachs ) {
        this.id = id;
        this.name = name;
        this.club = club;
        this.category = category;
        this.type = type;
        this.players = players;
        this.coachs = coachs;
    }

    public int getId(){ return id; }

    public String getName(){
        return name;
    }

    public Club getClub() { return club; }

    public String getCategory() { return category; }

    public String getType() { return type; }

    public List<User> getPlayers() {
        return players;
    }

    public List<User> getCoachs() {
        return coachs;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void addTeam(Team team){
        this.club.getTeams().add(team);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", club=" + club +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", players=" + players +
                ", coachs=" + coachs +
                '}';
    }

    public String toStringBis() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", club=" + club +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


}
