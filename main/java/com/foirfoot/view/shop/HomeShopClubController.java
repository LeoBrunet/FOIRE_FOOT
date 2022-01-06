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

import java.sql.SQLIntegrityConstraintViolationException;
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
            ProductComponent productComponent = new ProductComponent(Main.isClubCreatorOf(club.getCreator().getId()));
            productComponent.setProductName(product.getName());
            productComponent.setButtonViewAction(mouseEvent -> goToProduct(product));
            productComponent.setButtonDeleteAction(mouseEvent -> {
                try {
                    deleteProduct(product);
                } catch (ProductNotFoundException | SQLIntegrityConstraintViolationException e) {
                    e.printStackTrace();
                }
            });
            productComponent.setButtonAddAction(mouseEvent -> {
                try {
                    addProductTobasket(product);
                } catch (SQLIntegrityConstraintViolationException | ProductNotFoundException e) {
                    e.printStackTrace();
                }
            });
            listProducts.getChildren().add(productComponent);
        }
    }

    public void goToProduct(Product product) {
        Main.changeScene("shop/product", new ProductController(), new Object[]{product});
    }
    public void goToCreationProduct() {
        Main.changeScene("shop/formCreationProduct");
    }
    public void addProductTobasket(Product product) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        //Basket basket = new Basket(Main.connectedUser.getId(),product);
        System.out.println(Main.connectedUser.getId());
        System.out.println(Main.connectedUser.getBasket().getListProduct() );
        System.out.println(Main.connectedUser.getBasket());
        System.out.println(product);

        Main.connectedUser.getBasket().addProduct(product);
        facade.addProduct(Main.connectedUser.getBasket(), product);

    }

    public void deleteProduct(Product product) throws ProductNotFoundException, SQLIntegrityConstraintViolationException {
        facade.deleteProduct(product);

    }


    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }
}
