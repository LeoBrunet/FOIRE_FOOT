package com.foirfoot.view.user;

import com.foirfoot.model.Facade;
import com.foirfoot.model.User;
import com.foirfoot.view.Main;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLIntegrityConstraintViolationException;

public class UserController {

    public UserController() {

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
    @FXML
    private TextField userName;
    @FXML
    private TextField userFirstName;
    @FXML
    private Text changePageText;

    public void login() {
        if (userEmail.getText().isEmpty() || password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data");
        } else {
            try {
                User user = facade.login(userEmail.getText(), password.getText());
                //wrongLogIn.setStyle("-fx-font: 1.0 System");
                wrongLogIn.setStyle("-fx-font: 15.0 System;-fx-text-fill: #4f8000");
                wrongLogIn.setText("Connexion...");
                Main.setConnectedUser(user);
                Main.changeScene("home/home");
            } catch (WrongPasswordException | UserNotFoundException e) {
                wrongLogIn.setStyle("-fx-font: 15.0 System;-fx-text-fill: #e10000");
                wrongLogIn.setText(e.getMessage());
            }
        }
    }

    public void register() {
        if (userEmail.getText().isEmpty() || password.getText().isEmpty() || userName.getText().isEmpty() || userFirstName.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data");
        } else {

            try {
                facade.register(userName.getText(), userFirstName.getText(), userEmail.getText(), password.getText());
                //wrongLogIn.setStyle("-fx-font: 1.0 System");
                wrongLogIn.setStyle("-fx-font: 15.0 System;-fx-text-fill: #4f8000");
                goToLogin();
            } catch (SQLIntegrityConstraintViolationException e) {
                wrongLogIn.setStyle("-fx-font: 15.0 System;-fx-text-fill: #e10000");
                wrongLogIn.setText("Email address already used");
            }

        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) logInButton.getScene().getWindow();
        stage.close();
    }

    public void goToLogin() {
        Main.changeScene("user/login");
    }

    public void goToRegister() {
        Main.changeScene("user/register");
    }

}