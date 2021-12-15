package com.foirfoot.view;

import com.foirfoot.view.club.ClubController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class BarController {
    @FXML
    private Text home;
    @FXML
    private Text shop;
    @FXML
    private Text club;

    @FXML
    public void initialize(){
        if (Main.connectedUser.getClub() == null){
            hideClubText();
        }
    }

    public void goToHome(){
        Main.changeScene("home/home");
    }

    public void goToShop() {
        Main.changeScene("shop/shop");
    }

    public void goToClub() {
        Main.changeScene("club/club", new ClubController(), new Object[]{Main.connectedUser.getClub()});
    }

    public void goToProfile() {
        Main.changeScene("user/profile");
    }

    /**
     * Example of function to underline text in JavaFX text field.
     */
    public void underline(){
        home.setStyle("-fx-underline: true");
    }

    private void hideClubText(){
        club.setText("");
        club.setOnMouseClicked(null);
        club.setStyle("-margin-left:none");
    }
}
