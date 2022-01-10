package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.PaymentType;
import com.foirfoot.model.shop.Transaction;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

public class TransactionFormController extends Controller {
    Basket basket;
    Object payment;
    Transaction transaction;
    Facade facade = new Facade();
    @FXML
    private Button ValidInformation;
    @FXML
    private Button giveUp;

    @FXML
    private TextField userAddress;
    @FXML
    private TextField userCity;
    @FXML
    private TextField userCountry;
    @FXML
    private TextField stock;





    public void goToPayment() throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        Main.changeScene("shop/orderSummary",new OrderSummaryController(),new Object[]{facade.createTransaction(Main.connectedUser.getId(),Main.connectedUser.getBasket(), userAddress.getText(), userCity.getText(), userCountry.getText(),payment,Main.connectedUser.getBasket().getNbProducts(),Main.connectedUser.getBasket().calculTotal())});
    }
    public void giveUp(){
        Main.changeScene("shop/basket", new BasketController(),new Object[]{Main.connectedUser.getBasket()});
    }

    public void createTransaction() throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        if (userAddress.getText().isEmpty() || userCity.getText().isEmpty() ||userCountry.getText().isEmpty()) {
        } else {

//retrouver le user et le basket peutetreinutile nomprenom

            System.out.println(payment);

            //facade.createTransaction(Main.connectedUser,Main.connectedUser.getBasket(), userAddress.getText(), userCity.getText(), userCountry.getText(),payment);
            goToPayment();



        }
    }





    @Override
    public void setParameter(Object[] params) {
        this.payment = (Object) params[0];
    }



}
