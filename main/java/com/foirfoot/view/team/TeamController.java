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

public class TeamController extends Controller {
    private Club club;

    @FXML
    private Text teamName;
    @FXML
    private VBox teams;

    @FXML
    public void initialize(){
        System.out.println("initialize()");
        for (Team t : club.getTeams()) {
            System.out.println(t);
            teams.getChildren().add(new Text(t.getType() + " " + t.getCategory()));
        }
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }
}
