package com.foirfoot.view.club;

import com.foirfoot.model.category.Category;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import com.foirfoot.view.results.ResultsController;
import com.foirfoot.view.shop.HomeShopClubController;
import com.foirfoot.view.team.ListTeamsController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
    private ImageView editClub;

    @FXML
    public void initialize() {
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

        Image image = Main.downloadImage(club.getImageName(), club.getImageIS());
        clubImageView.setImage(image);

        if (Main.connectedUser.getClub() != null) {
            if (club.getCreator().getId() != Main.connectedUser.getClub().getId()) {
                editClub.setVisible(false);
            }
        }
    }

    public void goToShopClub() {
        Main.changeScene("shop/homeShopClub", new HomeShopClubController(), new Object[]{this.club});
    }

    public void goToResults(){
        for(Team t : club.getTeams()){
            System.out.println(t.getCategory());
            System.out.println(t.getType());
            if(t.getCategory().equals("SENIOR") && t.getType().equals("Team A")){
                System.out.println("ghfezgiufezufbe");
                Main.changeScene("results/results", new ResultsController(), new Object[]{t});
            }
        }
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }

    public void goToTeams() {
        if (Main.connectedUser.getClub().getTeams().isEmpty()) {
            Main.changeScene("team/no_team");
        } else {
            Main.changeScene("team/list_teams", new ListTeamsController(), new Object[]{club});
        }
    }

    public void goToClubModification() {
        Main.changeScene("club/clubCreationModification", new ClubCreationModificationController(), new Object[]{Main.connectedUser.getClub(), true});
    }
}
