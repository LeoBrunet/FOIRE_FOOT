package com.foirfoot.view.home;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.view.Main;
import com.foirfoot.view.club.ClubController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class HomeController {

    private final Facade facade = new Facade();

    @FXML
    private Text welcomeText;
    @FXML
    private VBox content;
    @FXML
    private TextField searchField;

    @FXML
    public void initialize() {
        welcomeText.setText("Welcome " + Main.connectedUser.getFirstName() + " !");
    }

    @FXML
    public void search() {
        //TODO Get players and club match with research
        Text search = new Text("Recherche : " + searchField.getText());
        search.setFont(new Font("Segoe UI", 10));
        search.setStyle("-fx-fill: grey");

        Text t = new Text("Club");
        t.setFont(new Font("Segoe UI Bold", 18));

        content.getChildren().clear();
        content.getChildren().add(search);
        content.getChildren().add(t);
        List<Club> clubs = facade.searchClubs(searchField.getText());
        for (Club club : clubs) {
            Text clubNameText = new Text(club.getName());
            clubNameText.setOnMouseClicked(mouseEvent -> goToClub(club));
            content.getChildren().add(clubNameText);
        }
    }

    public void goToClub(Club club){
        Main.changeScene("club/club", new ClubController(), new Object[]{club});
    }

}
