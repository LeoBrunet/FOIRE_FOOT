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
    public void initialize() {
        List<Product> products = new ArrayList<>();
        products = this.basket.getListProduct();


        for (Product product : products) {
            BasketProductComponent basketProductComponent = new BasketProductComponent();
            basketProductComponent.setProductName(product.getName());
            basketProductComponent.setProductPrice(product.getPrice() + "$");
            listProducts.getChildren().add(basketProductComponent);
        }
    }

    public void goToProduct(Product product) {
        Main.changeScene("shop/product", new ProductController(), new Object[]{product});
    }


    @Override
    public void setParameter(Object[] params) {
        this.basket = (Basket) params[0];
    }

    public void goToTransaction() {
        Main.changeScene("shop/PaymentType");
    }


}
