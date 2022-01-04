package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLIntegrityConstraintViolationException;

public class FormCreationProduct {
    Facade facade = new Facade();
    @FXML
    private Button addButton;
    @FXML
    private TextField nameProduct;
    @FXML
    private TextField descProduct;
    @FXML
    private TextField price;
    @FXML
    private TextField stock;

    public void goToHomeShopClub() {
        Main.changeScene("shop/homeShopSideClub");
    }



    public void createProduct() throws SQLIntegrityConstraintViolationException {
        if (nameProduct.getText().isEmpty() || descProduct.getText().isEmpty() || price.getText().isEmpty() || stock.getText().isEmpty()) {
        } else {


                facade.createProduct(nameProduct.getText(), descProduct.getText(), price.getText(), stock.getText(),Main.connectedUser.getClub().getId());
                //wrongLogIn.setStyle("-fx-font: 1.0 System");
                goToHomeShopClub();


        }
    }
}
