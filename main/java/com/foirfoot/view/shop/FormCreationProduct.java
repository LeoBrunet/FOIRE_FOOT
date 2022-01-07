package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        Main.changeScene("shop/homeShopClub", new HomeShopClubController(), new Object[]{Main.connectedUser.getClub()});
    }

    public void createProduct() throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        if (nameProduct.getText().isEmpty() || descProduct.getText().isEmpty() || price.getText().isEmpty() || stock.getText().isEmpty()) {
        } else {
                //Object prix = price.getText();

                facade.createProduct(nameProduct.getText(), descProduct.getText(), Integer.parseInt(price.getText()), stock.getText(),Main.connectedUser.getClub().getId());
                goToHomeShopClub();


        }
    }
}
