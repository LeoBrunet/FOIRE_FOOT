package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    public void goToPayment() {
        Main.changeScene("shop/PaymentType");
    }
    public void returnToBasket() {
        Main.changeScene("shop/basket");
    }

}
