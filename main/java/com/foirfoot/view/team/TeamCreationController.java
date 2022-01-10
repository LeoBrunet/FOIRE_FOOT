package com.foirfoot.view.team;

import com.foirfoot.dao.CategoryDAOMySQL;
import com.foirfoot.dao.TypeDAOMySQL;
import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.sql.SQLIntegrityConstraintViolationException;

public class TeamCreationController extends Controller {

    private final Facade facade = new Facade();
    private Club club;
    private boolean doublons = false;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Text result;
    @FXML
    private ComboBox category;
    @FXML
    private ComboBox type;

    @FXML
    public void initialize(){

        ObservableList<String> catList = FXCollections.observableArrayList(CategoryDAOMySQL.getCategoryList());
        ObservableList<String> typeList = FXCollections.observableArrayList(TypeDAOMySQL.getTypeList());


        category.setItems(catList);
        type.setItems(typeList);
        //scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @FXML
    public void createTeam(){
        if (category.getValue() == null || type.getValue() == null) {
            showTeamEmpty();
        } else {
            for (Team t : club.getTeams()) {
                if(category.getValue().toString().equals(t.getCategory()) && type.getValue().toString().equals(t.getType())){
                    doublons = true;

                }
            }
            if(!doublons){
                try {
                    Team team = facade.createTeam(category.getValue(), type.getValue(), Main.connectedUser.getClub());
                    System.out.print(Main.connectedUser.getClub());
                    Main.connectedUser.getClub().addTeam(team);
                    Main.changeScene("team/list_teams", new ListTeamsController(), new Object[]{Main.connectedUser.getClub()});
                }  catch (SQLIntegrityConstraintViolationException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                showTeamAlreadyExistsAlert();
                Main.changeScene("team/teamCreation", new TeamCreationController(), new Object[]{Main.connectedUser.getClub(), true});
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

    public void showTeamAlreadyExistsAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Team Creation");
        alert.setHeaderText("Results:");
        alert.setContentText("Team already exists !");

        alert.showAndWait();
    }

    public void showTeamEmpty() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Team Creation");
        alert.setHeaderText("Results:");
        alert.setContentText("Please enter your data");

        alert.showAndWait();
    }

}
