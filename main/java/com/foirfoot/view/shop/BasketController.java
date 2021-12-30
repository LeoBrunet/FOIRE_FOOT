package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BasketController {
    Facade facade = new Facade();
    @FXML
    private Button Valide;
    public void goToTransaction() {
        Main.changeScene("shop/transactionform");
    }



}
