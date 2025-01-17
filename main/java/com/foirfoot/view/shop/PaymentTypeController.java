package com.foirfoot.view.shop;

import com.foirfoot.dao.CategoryDAOMySQL;
import com.foirfoot.dao.TypeDAOMySQL;
import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.PaymentType;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentTypeController {
    Facade facade = new Facade();

    Object payment;

    @FXML
    private Label missPay;

    @FXML
    private Button ValidPayment;
    @FXML
    private ComboBox wayPay;

    List<String> liste = Arrays.asList("Cash", "Check", "CreditCard");

    ObservableList<String> paymentas = FXCollections.observableArrayList(liste);

        @FXML
        public void initialize() {
            wayPay.setItems(paymentas);
        }

    public void goToSummary() {
        if (wayPay.getValue() == null) {
            missPay.setText("Please enter payment");
        } else {
            this.payment = wayPay.getValue();


            System.out.println(payment.toString());
            Main.changeScene("shop/transactionform", new TransactionFormController(), new Object[]{payment});
        }
        }


    public Object getPayment(){


            Object payment = wayPay.getValue();

            System.out.println(payment.toString());

            return payment;

    }
    public void giveUp(){
            Main.changeScene("shop/basket", new BasketController(),new Object[]{Main.connectedUser.getBasket()});
    }




}
