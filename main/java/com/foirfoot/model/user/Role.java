package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;

import java.util.List;

public abstract class Role {
    private int clubId;
    private int teamId;
    private boolean isClubCreator;
    private List<Integer> exTeamsId;
    private Club club;
    private Team team;
    private List<Team> exTeams;


    public Role(int club_id, int teamId, boolean isClubCreator) {
        this.clubId = club_id;
        this.teamId = teamId;
        this.isClubCreator = isClubCreator;
    }

    public Role(int club_id, int teamId, boolean isClubCreator, List<Integer> exTeamsId) {
        this.clubId = club_id;
        this.teamId = teamId;
        this.isClubCreator = isClubCreator;
        this.exTeamsId = exTeamsId;
    }

    public Role(Club club, Team team, boolean isClubCreator) {
        this.isClubCreator = isClubCreator;
        this.club = club;
        this.team = team;
    }

    public Role(Club club, Team team, boolean isClubCreator, List<Team> exTeams) {
        this.isClubCreator = isClubCreator;
        this.club = club;
        this.team = team;
        this.exTeams = exTeams;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getTeam() {
        return teamId;
    }

    public void setTeam(int teamId) {
        this.teamId = teamId;
    }

    public boolean isClubCreator() {
        return isClubCreator;
    }

    public void setClubCreator(boolean clubCreator) {
        isClubCreator = clubCreator;
    }

    public List<Integer> getExTeams() {
        return exTeamsId;
    }

    public void setExTeams(List<Integer> exTeams) {
        this.exTeamsId = exTeams;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
