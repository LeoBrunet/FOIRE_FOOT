package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;

import java.util.List;

public class Player extends Role{
    private int height;
    private int weight;
    private String preferredPosition;


    public Player(Club club, Team team, boolean isClubCreator) {
        super(club, team, isClubCreator);
    }

    public Player(Club club, Team team, boolean isClubCreator, List<Team> exTeams) {
        super(club, team, isClubCreator, exTeams);
    }
}