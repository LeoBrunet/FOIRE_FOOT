package com.foirfoot.view.club;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Controller;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ClubController extends Controller {
    private Club club;
    @FXML
    private Text clubName;
    @FXML
    private Text clubAddress;
    @FXML
    private Text clubPhoneNumber;
    @FXML
    private Text clubWebsite;
    @FXML
    private VBox players;
    @FXML
    private VBox coaches;
    @FXML
    private ImageView clubImageView;

    @FXML
    public void initialize(){
        // TODO Corriger ordre nom et prénom
        clubName.setText(this.club.getName());
        clubAddress.setText(this.club.getAddress());
        clubPhoneNumber.setText(this.club.getPhoneNumber());
        clubWebsite.setText(this.club.getWebsite());
        System.out.println(club.getPlayers());
        System.out.println(club.getCoaches());

        for (User p : club.getPlayers()) {
            players.getChildren().add(new Text(p.getFirstName() + " " + p.getName()));
        }
        for (User c : club.getCoaches()) {
            coaches.getChildren().add(new Text(c.getFirstName() + " " + c.getName()));
        }

        clubImageView.setImage(new Image(club.getImageIS()));
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }
}
