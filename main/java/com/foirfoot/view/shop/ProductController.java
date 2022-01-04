package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductController extends Controller {
    private Product product;

    Facade facade = new Facade();
    @FXML
    public Label productName;

    @FXML
    public void initialize() {
        productName.setText(this.product.getName());
    }

    public void goToShop() {
        Main.changeScene("shop/homeShop");
    }

    @Override
    public void setParameter(Object[] params) {
        this.product = (Product) params[0];
    }
}
