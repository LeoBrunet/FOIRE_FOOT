package com.foirfoot.view.team;

import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NoTeamController {

    @FXML
    private Button createTeam;

    @FXML
    private void goToTeamCreation(){
        Main.changeScene("team/teamCreation");
    }

}