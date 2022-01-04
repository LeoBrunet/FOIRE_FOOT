package com.foirfoot.view.team;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ListTeamsController extends Controller {
    private Club club;

    @FXML
    private Text teamName;
    @FXML
    private VBox teams;
    @FXML
    private Text text;

    @FXML
    public void initialize(){
        System.out.println("initialize()");
        text.setText("List of " + Main.connectedUser.getName()+ " teams");
        for (Team t : club.getTeams()) {
            Text teamNameText = new Text(t.getType() + " " + t.getCategory());
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
        Main.changeScene("team/teamCreation");
    }

    @FXML
    private void goToTeam(Team team){
       /* for (Team t : club.getTeams()) {
            if(t.getCategory() == category)
            Text teamNameText = new Text(t.getType() + " " + t.getCategory());
            teamNameText.setOnMouseClicked(mouseEvent -> goToTeam(t));
        }*/

        Main.changeScene("team/team", new TeamController(), new Object[]{team});
    }

}
