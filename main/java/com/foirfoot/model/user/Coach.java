package com.foirfoot.model.user;

import java.util.List;

public class Coach extends Role{
    public Coach(int club, int team, boolean isClubCreator) {
        super(club, team, isClubCreator);
    }

    public Coach(int club, int team, boolean isClubCreator, List<Integer> exTeams) {
        super(club, team, isClubCreator, exTeams);
    }
}
