package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;

import java.util.List;

public class Coach extends Role{
    public Coach(Club club, Team team, boolean isClubCreator) {
        super(club, team, isClubCreator);
    }

    public Coach(Club club, Team team, boolean isClubCreator, List<Team> exTeams) {
        super(club, team, isClubCreator, exTeams);
    }
}
