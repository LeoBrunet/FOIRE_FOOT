package com.foirfoot.view;

import com.foirfoot.model.Facade;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserController {

    public UserController(){

    }
    Facade facade = new Facade();
    @FXML
    private Button logInButton;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrongLogIn;

    public void login() {
        // Main m = new Main(); autre vue (pour la connexion)
        if(userEmail.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogIn.setText("Please enter your data");
        } else {
            try {
                facade.login(userEmail.getText(), password.getText());
                //wrongLogIn.setStyle("-fx-font: 1.0 System");
                wrongLogIn.setStyle("-fx-font: 15.0 System;-fx-text-fill: #4f8000");
                wrongLogIn.setText("Connexion...");
                //m.changeScene("afterLogin.fxml");
            } catch (WrongPasswordException | UserNotFoundException e) {
                wrongLogIn.setStyle("-fx-font: 15.0 System;-fx-text-fill: #e10000");
                wrongLogIn.setText(e.getMessage());
            }
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) logInButton.getScene().getWindow();
        stage.close();
    }

}