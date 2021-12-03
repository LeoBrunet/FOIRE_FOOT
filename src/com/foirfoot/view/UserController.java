package com.foirfoot.view;

import com.foirfoot.model.Facade;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
                wrongLogIn.setText("success");
                //m.changeScene("afterLogin.fxml");
            } catch (WrongPasswordException e) {
                wrongLogIn.setText("Wrong password");
            } catch (UserNotFoundException e) {
                wrongLogIn.setText("Wrong user");
            }
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) logInButton.getScene().getWindow();
        stage.close();
    }

}