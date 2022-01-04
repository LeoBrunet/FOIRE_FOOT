package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private Button but;


    @FXML
    public void initialize() {
        content.getChildren().clear();

        Text productsText = new Text("Products");
        content.getChildren().add(productsText);
        List<Product> products = new ArrayList<>();
        try {
            products = facade.getAllProducts();
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }

        for (Product product : products) {
            Text productName = new Text(product.getName());
            productName.setOnMouseClicked(mouseEvent -> goToProduct(product));
            System.out.println(product.getName());
        }
    }

    public void goToProduct(Product product) {
        Main.changeScene("shop/product", new ProductController(), new Object[]{product});
    }
}
