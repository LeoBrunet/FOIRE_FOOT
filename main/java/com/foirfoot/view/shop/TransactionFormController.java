package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLIntegrityConstraintViolationException;

public class TransactionFormController {
    Facade facade = new Facade();
    @FXML
    private Button ValidInformation;
    @FXML
    private Button GiveUpTransaction;
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

    /*public void createTransaction() throws SQLIntegrityConstraintViolationException {
        if (userName.getText().isEmpty() || userFirstName.getText().isEmpty() || userAddress.getText().isEmpty() || userCity.getText().isEmpty() ||userCountry.getText().isEmpty()) {
        } else {

//retrouver le user et le basket peutetreinutile nomprenom
            facade.createTransaction(userName.getText(), userFirstName.getText(), userAddress.getText(), userCity.getText(), userCountry.getText());
            //goToHomeShopClub();


        }
    }*/

}
