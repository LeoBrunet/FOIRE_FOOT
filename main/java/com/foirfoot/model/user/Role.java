package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;

import java.util.List;

public abstract class Role {
    private Club club;
    private Team team;
    private boolean isClubCreator;
    private List<Team> exTeams;

    public Role(Club club, Team team, boolean isClubCreator) {
        this.club = club;
        this.team = team;
        this.isClubCreator = isClubCreator;
    }

    public Role(Club club, Team team, boolean isClubCreator, List<Team> exTeams) {
        this.club = club;
        this.team = team;
        this.isClubCreator = isClubCreator;
        this.exTeams = exTeams;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isClubCreator() {
        return isClubCreator;
    }

    public void setClubCreator(boolean clubCreator) {
        isClubCreator = clubCreator;
    }

    public List<Team> getExTeams() {
        return exTeams;
    }

    public void setExTeams(List<Team> exTeams) {
        this.exTeams = exTeams;
    }
}
