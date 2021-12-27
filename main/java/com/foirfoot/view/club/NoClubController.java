package com.foirfoot.view.club;

import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NoClubController {

    @FXML
    private Button createClub;

    @FXML
    private void goToClubCreation(){
        Main.changeScene("club/clubCreation");
    }
}
