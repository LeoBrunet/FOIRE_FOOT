package com.foirfoot.view.team;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import com.foirfoot.view.club.ClubController;
import com.foirfoot.view.club.ClubCreationModificationController;
import comparator.TeamComparator;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListTeamsController extends Controller {
    private Club club;
    List<Team> teamOrd = new ArrayList<>();

    @FXML
    private Text teamName;
    @FXML
    private VBox teams;
    @FXML
    private Text text;

    @FXML
    public void initialize(){
        System.out.println("initialize()");
        teamOrd = club.getTeams();
        TeamComparator teamComparator = new TeamComparator();
        Collections.sort(teamOrd, teamComparator);
        text.setText("List of " + Main.connectedUser.getName()+ " teams");
        for (Team t : club.getTeams()) {
            Text teamNameText = new Text(t.getType() + " - " + t.getCategory());
            teamNameText.setOnMouseClicked(mouseEvent -> goToTeam(t));
            teams.getChildren().add(teamNameText);
        }
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }

    @FXML
    private void goToTeamCreation(){
        Main.changeScene("team/teamCreation", new TeamCreationController(), new Object[]{Main.connectedUser.getClub(), true});
    }

    @FXML
    private void goToTeam(Team team){
        Main.changeScene("team/team", new TeamController(), new Object[]{team});
    }

    public void goToClub() {
        if (Main.connectedUser.getClub() == null){
            Main.changeScene("club/no_club");
        } else {
            System.out.println(Main.connectedUser.getClub());
            Main.changeScene("club/club", new ClubController(), new Object[]{Main.connectedUser.getClub()});
        }
    }

}
