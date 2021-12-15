package com.foirfoot.model.club;

import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.User;

import java.io.InputStream;
import java.util.List;

public class Club {
    private String name;
    private String address;
    private String phoneNumber;
    private String website;
    private User creator;
    private List<User> players;
    private List<User> coachs;
    private List<Team> teams;
    private InputStream imageIS;

    public Club(String name, String address, String phoneNumber, String website, User creator, List<User> players, List<User> coachs, List<Team> teams, InputStream imageIS) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.creator = creator;
        this.players = players;
        this.coachs = coachs;
        this.teams = teams;
        this.imageIS = imageIS;
    }

    public Club(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getPlayers() {
        return players;
    }

    public List<User> getCoaches() {
        return coachs;
    }

    public User getCreator() {
        return creator;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public InputStream getImageIS() {
        return imageIS;
    }
}
