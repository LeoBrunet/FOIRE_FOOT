package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Product;
import com.foirfoot.model.shop.Transaction;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class OrdersListController extends Controller {
    Facade facade = new Facade();
    Transaction transaction;

    @FXML
    private VBox listOrders;



    @FXML
    public void initialize() throws ProductNotFoundException {


        List<Transaction> transactions = new ArrayList<>();
        transactions = facade.getAllTransactions(Main.connectedUser.getId());

        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
            System.out.println(transaction.getTotal());
            OrderComponent orderComponent = new OrderComponent();
            orderComponent.setNbProducts(Integer.toString(transaction.getNbProducts()) + " products");
            orderComponent.setTotal("Total : " +transaction.getTotal() + "$");
            listOrders.getChildren().add(orderComponent);
        }


    }


    @Override
    public void setParameter(Object[] params) {
        this.transaction = (Transaction) params[0];
    }
}
