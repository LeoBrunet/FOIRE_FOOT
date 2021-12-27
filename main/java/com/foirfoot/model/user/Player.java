package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;

import java.util.List;

public class Player extends Role{
    private int height;
    private int weight;
    private String preferredPosition;


    public Player(int club, int team, boolean isClubCreator) {
        super(club, team, isClubCreator);
    }

    public Player(int club, int team, boolean isClubCreator, List<Integer> exTeams) {
        super(club, team, isClubCreator, exTeams);
    }
}
