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
        System.out.println("initialize()");
        clubName.setText(this.club.getName());
        clubAddress.setText(this.club.getAddress());
        clubPhoneNumber.setText(this.club.getPhoneNumber());
        clubWebsite.setText(this.club.getWebsite());

        for (User p : club.getPlayers()) {
            players.getChildren().add(new Text(p.getFirstName() + " " + p.getName()));
        }
        for (User c : club.getCoaches()) {
            coaches.getChildren().add(new Text(c.getFirstName() + " " + c.getName()));
        }

        // TODO Image s'affiche que la premiere fois
        // voir si le problème vient de sardine l 37 de ClubDAO
        // essayer d'enregistrer une imag plutot qu'une InputStream
        // clubImageView.setImage(club.image);
        Image i = new Image(club.getImageIS());
        clubImageView.setImage(i);
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }
}
