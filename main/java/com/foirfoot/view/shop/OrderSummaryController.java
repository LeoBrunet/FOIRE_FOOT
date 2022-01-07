package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Transaction;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrderSummaryController extends Controller {
    Facade facade = new Facade();
    Transaction transaction;
    @FXML
    private Button SeeOrders;
    @FXML
    public Label address;
    @FXML
    public Label payment;
    @FXML
    public Label total;

    @FXML
    public void initialize() {
        address.setText(this.transaction.getaddress()+this.transaction.getCity()+this.transaction.getCountry());
        payment.setText((String)this.transaction.getPayment());
        total.setText(Integer.toString(this.transaction.getBasket().calculTotal()));


    }

    public void goToSeeAll() {
        System.out.println(transaction.getId());
        Main.changeScene("shop/ordersList");

    }
    @Override
    public void setParameter(Object[] params) {
        this.transaction = (Transaction) params[0];
    }
}
