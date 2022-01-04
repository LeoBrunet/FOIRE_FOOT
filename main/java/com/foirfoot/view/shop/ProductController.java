package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Main;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductController {
    private Product product;

    Facade facade = new Facade();
    @FXML
    public Text text;
    @FXML
    public Text shopping;


   // @FXML
   // public void initialize(){
        //text.setText("Look, " + Main.selectedProduct.getName()+ ".");
   // }
   public void goToShop() {
       Main.changeScene("shop/homeShop");
   }




}
