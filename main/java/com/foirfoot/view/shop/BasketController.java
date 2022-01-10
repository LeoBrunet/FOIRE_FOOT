package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BasketController extends Controller {
    Facade facade = new Facade();

    private Basket basket;
    @FXML
    private VBox listProducts;
    @FXML
    private Button valid;
    @FXML
    private Label Total;

    @FXML
    public void initialize() throws ProductNotFoundException {
        List<Product> products = this.basket.getListProduct();
        List<Integer> quantities = this.basket.getListQuantity();
        Total.setText(this.basket.calculTotal() + "$");
        int i = 0;

        for (Product product : products) {

                BasketProductComponent basketProductComponent = new BasketProductComponent();
                basketProductComponent.setProductName(product.getName());
                basketProductComponent.setProductQuantity(facade.getQuantityOfProduct(product.getId()));

                basketProductComponent.setProductPrice(product.getPrice()*facade.getQuantityOfProduct(product.getId()) + "$");
                basketProductComponent.setProductImage(Main.downloadImage(product.getImageName(), product.getImageIS()));

                listProducts.getChildren().add(basketProductComponent);


        }
    }

    public void goToProduct(Product product) {
        Main.changeScene("shop/product", new ProductController(), new Object[]{product});
    }
    public void deleteAll() throws ProductNotFoundException {
        basket.deleteAll();
        facade.deleteAll();
        initialize();
    }


    @Override
    public void setParameter(Object[] params) {
        this.basket = (Basket) params[0];
    }

    public void goToTransaction() {
        Main.changeScene("shop/PaymentType");
    }


}
