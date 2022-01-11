package com.foirfoot.view.user;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.Role;
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
    List<Club> clubs;
    private String typeEdit;
    private Facade facade = new Facade();

    public void initialize() {

        text.setText("Personnal " + typeEdit + " edition.");
        valid.setOnMouseClicked(mouseEvent -> {Main.changeScene("user/profile");});
        if (typeEdit.equals("Team")) {
            try {
                List<Team> teams = facade.getAllTeams();
                for (Team t : teams){
                    choiceBox.getItems().add(t.getCategory() + " " + t.getType());
                }
            } catch (TeamNotFoundException ignored) {
            }
        } else if (typeEdit.equals("Club")) {
            try {
                List<Club> clubs = facade.getAllClubs();
                for (Club c : clubs){
                    choiceBox.getItems().add(c.getName());
                }
            } catch (ClubNotFoundException ignored) {
            }
        } else {
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
                Main.changeScene("user/profile");
            });
        }
    }

    @Override
    public void setParameter(Object[] params) {
        typeEdit = (String) params[0];
    }
}
