package com.foirfoot.model.user;

import java.util.List;

public class ClassicUser extends Role{
    public ClassicUser(int club, int team, boolean isClubCreator) {
        super(club, team, isClubCreator);
    }

    public ClassicUser(int club, int team, boolean isClubCreator, List<Integer> exTeams) {
        super(club, team, isClubCreator, exTeams);
    }
}
