package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class HomeShopClubController extends Controller {
    Facade facade = new Facade();

    private Club club;
    @FXML
    private FlowPane listProducts;
    @FXML
    private Button addProduct;

    @FXML
    public void initialize() {

        if (!Main.isClubCreatorOf(club.getCreator().getId())) {
            this.addProduct.setVisible(false);
        }
        List<Product> products = new ArrayList<>();
        try {
            products = facade.getAllProducts();
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }

        for (Product product : products) {
            ProductComponent productComponent = new ProductComponent(Main.isClubCreatorOf(club.getId()));
            productComponent.setProductName(product.getName());
            productComponent.setButtonViewAction(mouseEvent -> goToProduct(product));
            listProducts.getChildren().add(productComponent);
        }
    }

    public void goToProduct(Product product) {
        Main.changeScene("shop/product", new ProductController(), new Object[]{product});
    }
    public void goToCreationProduct() {
        Main.changeScene("shop/formCreationProduct");
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }
}
