package com.foirfoot.view.team;

import com.foirfoot.dao.CategoryDAOMySQL;
import com.foirfoot.dao.TypeDAOMySQL;
import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.sql.SQLIntegrityConstraintViolationException;

public class TeamCreationController extends Controller {

    private final Facade facade = new Facade();
    private Club club;

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

        System.out.print(catList.toString());

        category.setItems(catList);
        type.setItems(typeList);
        System.out.println(category);
        System.out.println(scrollPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @FXML
    public void createTeam(){
        result.setText("Team being created...");
        if (category.getValue() == null || type.getValue() == null) {
            result.setStyle("-fx-text-fill: #e10000");
            result.setText("Please enter your data");
        } else {
            try {
                Team team = facade.createTeam(category.getValue(), type.getValue(), Main.connectedUser.getClub());
                System.out.print(Main.connectedUser.getClub());
                Main.connectedUser.getClub().addTeam(team);
                Main.changeScene("team/list_teams", new ListTeamsController(), new Object[]{Main.connectedUser.getClub()});
            }  catch (SQLIntegrityConstraintViolationException throwables) {
                throwables.printStackTrace();
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }


/*
    public void goToTeam() {
        if (Main.connectedUser.getClub().getTeam){
            Main.changeScene("club/no_club");
        } else {
            Main.changeScene("club/club", new ClubController(), new Object[]{Main.connectedUser.getClub()});
        }
    }
*/

}
