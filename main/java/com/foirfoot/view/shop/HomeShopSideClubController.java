package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Product;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.foirfoot.view.Main;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class HomeShopSideClubController {
    Facade facade = new Facade();



    private Product product;
    @FXML
    private AnchorPane content;
    @FXML
    private Text productName;
    @FXML
    private Text productDesc;
    @FXML
    private Text productPrice;
    @FXML
    private Button show;


    public HomeShopSideClubController() {
    }

   public void showProduct()  {
       content.getChildren().clear();

       Text productsText = new Text("Products");
       content.getChildren().add(productsText);
       List<Product> products = facade.showProduct();
       for (Product product : products) {
               Text productName = new Text(product.getName());
               System.out.println(product.getName());
       }
   }
}
