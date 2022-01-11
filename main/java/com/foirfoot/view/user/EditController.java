package com.foirfoot.view.user;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ClubNotFoundException;
import exceptions.TeamNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class EditController extends Controller {

    @FXML
    public Text text;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public Button valid;

    private String typeEdit;
    private final Facade facade = new Facade();

    public void initialize() {

        text.setText("Personnal " + typeEdit + " edition.");
        if (typeEdit.equals("Team")) {
            try {
                if (Main.connectedUser.getTeam() != null) {
                    choiceBox.setValue(Main.connectedUser.getTeam().getCategory() + " " + Main.connectedUser.getTeam().getType());
                }
                List<Team> teams = facade.getAllTeams();
                for (Team t : teams){
                    choiceBox.getItems().add(t.getCategory() + " " + t.getType());
                }
                valid.setOnMouseClicked(mouseEvent -> {
                    Team team = null;
                    for (Team t : teams) {
                        if (t.getName().equals(choiceBox.getValue())){
                            team = t;
                        }
                    }
                    Main.connectedUser.setTeam(team);
                    facade.updateUser(Main.connectedUser);
                    Main.changeScene("user/profile");
                });
            } catch (TeamNotFoundException ignored) {
            }
        } else if (typeEdit.equals("Club")) {
            try {
                if (Main.connectedUser.getClub() != null) {
                    choiceBox.setValue(Main.connectedUser.getClub().getName());
                }
                List<Club> clubs = facade.getAllClubs();
                for (Club c : clubs){
                    choiceBox.getItems().add(c.getName());
                }
                valid.setOnMouseClicked(mouseEvent -> {
                    Club club = null;
                    for (Club c : clubs) {
                        if (c.getName().equals(choiceBox.getValue())){
                            club = c;
                        }
                    }
                    Main.connectedUser.setClub(club);
                    Main.connectedUser.setIsClubCreator(false);
                    facade.updateUser(Main.connectedUser);
                    Main.changeScene("user/profile");
                });
            } catch (ClubNotFoundException ignored) {
            }
        } else {
            choiceBox.setValue(Main.connectedUser.getRoleName().name());
            List<RoleName> roleNames = Arrays.asList(RoleName.values());
            for (RoleName r : roleNames){
                choiceBox.getItems().add(r.name());
            }
            valid.setOnMouseClicked(mouseEvent -> {
                RoleName newRoleName = RoleName.classic;
                for (RoleName r:roleNames) {
                    if (r.name().equals(choiceBox.getValue())){
                        newRoleName = r;
                    }
                }
                Main.connectedUser.setRoleName(newRoleName);
                facade.updateUser(Main.connectedUser);
                Main.changeScene("user/profile");
            });
        }
    }

    @Override
    public void setParameter(Object[] params) {
        typeEdit = (String) params[0];
    }
}
