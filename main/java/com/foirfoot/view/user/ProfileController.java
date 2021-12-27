package com.foirfoot.view.user;

import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProfileController {
    @FXML
    public Text text;

    @FXML
    public void initialize(){
        text.setText("Bienvenue, " + Main.connectedUser.getFirstName()+ ".");
    }
}
