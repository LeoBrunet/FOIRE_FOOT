package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BasketController {
    Facade facade = new Facade();
    @FXML
    private Button validButton;

    public void goToPayment() {
        Main.changeScene("shop/PaymentType");
    }

}