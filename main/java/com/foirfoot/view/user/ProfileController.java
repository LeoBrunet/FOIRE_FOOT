package com.foirfoot.view.user;

import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProfileController {
    @FXML
    public Text text;
    @FXML
    public ImageView basket;
    @FXML
    public ImageView logout;
    @FXML
    public ImageView settings;

    @FXML
    public void initialize(){
        text.setText("Bienvenue, " + Main.connectedUser.getFirstName()+ ".");
        logout.setOnMouseClicked(mouseEvent -> logOut());
    }

    @FXML
    public void logOut(){
        Main.changeScene("user/login");
    }

    @FXML
    public void goToBasket(){
        Main.changeScene("shop/basket");
    }

    @FXML
    public void goToSettings(){
        Main.changeScene("user/setting");
    }
}
