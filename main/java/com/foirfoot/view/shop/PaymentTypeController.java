package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.PaymentType;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class PaymentTypeController {
    Facade facade = new Facade();
    @FXML
    private RadioButton cash;
    @FXML
    private RadioButton cheque;
    @FXML
    private RadioButton creditCard;
    @FXML
    private Button ValidPayment;

    public void goToSummary() {
        Main.changeScene("shop/orderSummary");
    }

    /*public void getPayment(){


            goToSummary();



    }*/
}
