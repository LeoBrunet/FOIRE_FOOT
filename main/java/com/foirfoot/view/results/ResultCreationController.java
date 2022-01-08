package com.foirfoot.view.results;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.result.Result;
import com.foirfoot.model.team.Team;
import com.foirfoot.view.Main;
import com.foirfoot.view.club.ClubController;
import exceptions.TeamNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;

public class ResultCreationController {
    private final Facade facade = new Facade();
    @FXML
    private TextField id_ht;
    @FXML
    private TextField id_ot;
    @FXML
    private Spinner<Integer> score_ht;
    @FXML
    private Spinner<Integer> score_ot;

    public void initialize(){
        SpinnerValueFactory<Integer> valueFactory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30);
        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30);

        valueFactory1.setValue(0);
        valueFactory2.setValue(0);
        score_ot.setValueFactory(valueFactory1);
        score_ht.setValueFactory(valueFactory2);



    }

    private boolean verifiedEntries() {
        if (id_ht.getText().isEmpty() || id_ot.getText().isEmpty() || score_ht.getValue() == null || score_ot.getValue() == null ) {

            return false;
        }
        return true;
    }

    public void createResult(){
        if (verifiedEntries()) {
            try {
                System.out.println(Integer.valueOf(id_ht.getText()));
                Team homeTeam = facade.getTeam(Integer.parseInt(id_ht.getText()));
                Team outsideTeam = facade.getTeam(Integer.parseInt(id_ot.getText()));
                Result result = facade.createResult((Integer) score_ht.getValue(), (Integer) score_ot.getValue(), homeTeam, outsideTeam);
                goToClub();
            } catch (SQLIntegrityConstraintViolationException | TeamNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            showResultEmpty();
        }

    }

    public void showResultEmpty() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result Creation");
        alert.setHeaderText("Results:");
        alert.setContentText("Please enter your data");

        alert.showAndWait();
    }

    public void goToClub() {
            Main.changeScene("home/home");
    }

    public void goToHome(){
        Main.changeScene("home/home");
    }
}
