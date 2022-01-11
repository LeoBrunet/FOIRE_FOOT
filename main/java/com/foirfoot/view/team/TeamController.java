package com.foirfoot.view.team;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import com.foirfoot.view.results.ResultsController;
import com.foirfoot.view.shop.HomeShopClubController;
import exceptions.ProductNotFoundException;
import exceptions.TeamNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TeamController extends Controller {
    private final Facade facade = new Facade();
    private Team team;
    @FXML
    private Text teamName;
    @FXML
    private Text numberPlayer;
    @FXML
    private Text category;
    @FXML
    private Text type;
    @FXML
    private VBox players;
    @FXML
    private VBox coaches;
    @FXML
    private ImageView clubImageView;

    public void initialize() {
        System.out.println("initialize()");
        teamName.setText(team.getType() + " - " + team.getCategory() + " - " + team.getClub().getName());
        category.setText(team.getCategory());
        type.setText(team.getType());
        try {
            team = facade.getTeam(team.getId());
        } catch (TeamNotFoundException e) {
            e.printStackTrace();
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(team);

        for (User p : team.getPlayers()) {
            players.getChildren().add(new Text(p.getFirstName() + " " + p.getName()));
        }
        for (User c : team.getCoachs()) {
            coaches.getChildren().add(new Text(c.getFirstName() + " " + c.getName()));
        }
        this.numberPlayer.setText(String.valueOf(team.getPlayers().size()));

        //TODO Delete files
        File file = new File(System.getProperty("user.dir") + "/main/java/com/foirfoot/view/assets/images/temp/" + team.getClub().getImageName());
        clubImageView.setImage(new Image(file.toURI().toString()));
    }

    public void goToTeams() {
        if (Main.connectedUser.getClub().getTeams().isEmpty()) {
            Main.changeScene("team/no_team");
        } else {
            Main.changeScene("team/list_teams", new ListTeamsController(), new Object[]{team.getClub()});
        }
    }
    public void apply(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(team.getClub().getName());
        alert.setHeaderText( "Please send your request by mail" + "\n" + team.getClub().getName() + " contact details : ");
        alert.setContentText(team.getClub().getName()+ " adress : "+ team.getClub().getAddress() + "\n" +
                team.getClub().getName()+ " phone number : "+ team.getClub().getPhoneNumber() + "\n" +
                team.getClub().getName()+ " website : "+ team.getClub().getWebsite());


        alert.showAndWait();
    }

    public void goToShopClub() {
        Main.changeScene("shop/homeShopClub", new HomeShopClubController(), new Object[]{this.team.getClub()});
    }

    public void goToResults(){
        Main.changeScene("results/results", new ResultsController(), new Object[]{team});
    }

    @Override
    public void setParameter(Object[] params) {
        this.team = (Team) params[0];
    }
}
