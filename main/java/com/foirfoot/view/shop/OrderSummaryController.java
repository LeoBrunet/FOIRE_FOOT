package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OrderSummaryController {
    Facade facade = new Facade();
    @FXML
    private Button SeeOrders;
    public void goToSeeAll() {
        Main.changeScene("shop/ordersList");
    }
}
