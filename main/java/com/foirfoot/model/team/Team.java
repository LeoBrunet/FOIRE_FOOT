package com.foirfoot.model.team;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;

import java.util.Objects;

public class Team {
    private int id;
    private String name;
    private Club club;
    private String category;
    private String type;

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

    public int getId(){ return id; }

    public String getName(){
        return name;
    }

    public Club getClub() { return club; }

    public String getCategory() { return category; }

    public String getType() { return type; }

    public void setId(int id) {
        this.id = id;
    }

}
