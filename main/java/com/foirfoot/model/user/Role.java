package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;

import java.util.ArrayList;
import java.util.List;

public abstract class Role {
    private boolean isClubCreator;
    private Club club;
    private Team team;
    private List<Team> exTeams;


    public Role(int club_id, int teamId, boolean isClubCreator) {
        this.club = new Club(club_id);
        this.team = new Team(teamId);
        this.isClubCreator = isClubCreator;
    }

    public Role(int club_id, int teamId, boolean isClubCreator, List<Integer> exTeams) {
        this(club_id, teamId, isClubCreator);
        this.exTeams = new ArrayList<>();
        for (int exTeamId : exTeams) {
            this.exTeams.add(new Team(exTeamId));
        }
    }

    public boolean isClubCreator() {
        return isClubCreator;
    }

    public void setClubCreator(boolean clubCreator) {
        isClubCreator = clubCreator;
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

    public List<Team> getExTeams() {
        return exTeams;
    }

    public void setExTeams(List<Team> exTeams) {
        this.exTeams = exTeams;
    }

    @Override
    public String toString() {
        return "Role{" +
                "isClubCreator=" + isClubCreator +
                ", club=" + club.toStringBis() +
                ", team=" + team +
                ", exTeams=" + exTeams +
                '}';
    }
}
