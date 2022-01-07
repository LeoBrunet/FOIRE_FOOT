package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.PaymentType;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLIntegrityConstraintViolationException;

public class TransactionFormController {
    Basket basket;

    Facade facade = new Facade();
    @FXML
    private Button ValidInformation;
    @FXML
    private Button giveUp;
    @FXML
    private TextField userName;
    @FXML
    private TextField userFirstName;
    @FXML
    private TextField userAddress;
    @FXML
    private TextField userCity;
    @FXML
    private TextField userCountry;
    @FXML
    private TextField stock;

    public void goToPayment() {
        Main.changeScene("shop/PaymentType");
    }
    public void returnToBasket() {
        Main.changeScene("shop/basket");
    }

    public void createTransaction() throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        if (userAddress.getText().isEmpty() || userCity.getText().isEmpty() ||userCountry.getText().isEmpty()) {
        } else {

//retrouver le user et le basket peutetreinutile nomprenom
            facade.createTransaction(Main.connectedUser,Main.connectedUser.getBasket(), userAddress.getText(), userCity.getText(), userCountry.getText(),PaymentType.Cash);
            goToPayment();


        }
    }


}
