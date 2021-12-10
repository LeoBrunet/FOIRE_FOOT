package com.foirfoot.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class BarController {
    @FXML
    private Text home;
    @FXML
    private Text shop;
    @FXML
    private Text clubs;
    @FXML
    private Text players;

    public void goToHome(){
        Main.changeScene("home/home");
    }

    public void goToShop() {
        Main.changeScene("shop/shop");
    }

    /**
     * Example of fonction to underline text in JavaFX text field.
     */
    public void underline(){
        home.setStyle("-fx-underline: true");
    }
}
