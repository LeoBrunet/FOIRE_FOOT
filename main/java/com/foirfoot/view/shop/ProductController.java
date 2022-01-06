package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ClubNotFoundException;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProductController extends Controller {
    private Product product;

    Facade facade = new Facade();
    @FXML
    public Label productName;
    @FXML
    public Label productDesc;
    @FXML
    public Label productPrice;

    @FXML
    public void initialize() {
        productName.setText(this.product.getName());
        productDesc.setText(this.product.getDescription());
        productPrice.setText(this.product.getPrice());

    }

    public void goToShop() {
        try {
            Main.changeScene("shop/homeShopClub", new HomeShopClubController(), new Object[]{facade.getClub(product.getClubId())});
        } catch (ClubNotFoundException e) {
            e.printStackTrace();
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setParameter(Object[] params) {
        this.product = (Product) params[0];
    }
}
