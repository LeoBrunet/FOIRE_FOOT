package com.foirfoot.view.home;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Main;
import com.foirfoot.view.club.ClubController;
import exceptions.ProductNotFoundException;
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
    public void search() throws ProductNotFoundException {
        content.getChildren().clear();

        // Sum up of the research
        Text search = new Text("Recherche : " + searchField.getText());
        search.setFont(new Font("Segoe UI", 10));
        search.setStyle("-fx-fill: grey");
        content.getChildren().add(search);

        // RESULT OF THE RESEARCH
        // Clubs
        Text clubsText = new Text("Clubs");
        clubsText.setFont(new Font("Segoe UI Bold", 18));
        content.getChildren().add(clubsText);

        List<Club> clubs = facade.searchClubs(searchField.getText());
        for (Club club : clubs) {
            Text clubNameText = new Text(club.getName());
            clubNameText.setOnMouseClicked(mouseEvent -> goToClub(club));
            content.getChildren().add(clubNameText);
        }

        // Users
        Text usersText = new Text("Users");
        usersText.setFont(new Font("Segoe UI Bold", 18));
        content.getChildren().add(usersText);

        List<User> users = facade.searchUsers(searchField.getText());
        for (User user : users) {
            Text clubNameText = new Text(user.getFirstName() + " " + user.getName());
            //clubNameText.setOnMouseClicked(mouseEvent -> goToClub(club));
            content.getChildren().add(clubNameText);
        }
    }

    public void goToClub(Club club){
        Main.changeScene("club/club", new ClubController(), new Object[]{club});
    }

}
