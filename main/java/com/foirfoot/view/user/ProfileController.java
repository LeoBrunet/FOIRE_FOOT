package com.foirfoot.view.user;

import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProfileController {
    @FXML
    public Text text;
    @FXML
    public Text role;
    @FXML
    public Text club;
    @FXML
    public Text team;
    @FXML
    public ImageView basket;
    @FXML
    public ImageView logout;
    @FXML
    public ImageView settings;
    @FXML
    public Button roleEdit;
    @FXML
    public Button clubEdit;
    @FXML
    public Button teamEdit;

    @FXML
    public void initialize() {
        text.setText("Bienvenue, " + Main.connectedUser.getFirstName() + ".");
        logout.setOnMouseClicked(mouseEvent -> logOut());
        role.setText("Role: " + Main.connectedUser.getRoleName());
        if (Main.connectedUser.getClub() != null) {
            club.setText("Club: " + Main.connectedUser.getClub().getName());
        } else {
            club.setText("Club: You are not in a club.");
        }
        if (Main.connectedUser.getTeam().getCategory() != null) {
            team.setText("Team: " + Main.connectedUser.getTeam().getCategory() + " " + Main.connectedUser.getTeam().getType());
        } else {
            team.setText("Team: You are not in a team.");
        }

        roleEdit.setOnMouseClicked(mouseEvent -> Main.changeScene("user/edit", new EditController(), new Object[]{"Role"}));
        teamEdit.setOnMouseClicked(mouseEvent -> Main.changeScene("user/edit", new EditController(), new Object[]{"Team"}));
        clubEdit.setOnMouseClicked(mouseEvent -> Main.changeScene("user/edit", new EditController(), new Object[]{"Club"}));
    }

    @FXML
    public void logOut() {
        Main.changeScene("user/login");
    }

    @FXML
    public void goToBasket() {
        Main.changeScene("shop/basket");
    }

    @FXML
    public void goToSettings() {
        Main.changeScene("user/setting");
    }
}
