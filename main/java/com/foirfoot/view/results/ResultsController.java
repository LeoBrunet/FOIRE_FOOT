package com.foirfoot.view.results;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ResultsController {

    @FXML
    public Text text;
    Facade facade = new Facade();

    public void initialize(){
        try {
            text.setText("Latest results -- " + Main.connectedUser.getFirstName()+ ".");
            facade.displayResult();

        }

    }
}
