package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Product;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ClubNotFoundException;
import exceptions.ProductNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

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
    private Button addProduct;
    @FXML
    private ComboBox quantity;

    @FXML
    public ImageView pictureProduct;
    /*@FXML
    public Button addToBasket;*/
    List<String> liste = Arrays.asList("1", "2", "3","4","5");

    ObservableList<String> numbers = FXCollections.observableArrayList(liste);



    @FXML
    public void initialize() {
        int price = this.product.getPrice();
        quantity.setItems(numbers);
        productName.setText(this.product.getName());
        productDesc.setText(this.product.getDescription());
        productPrice.setText(price + "$");
        setProductImage(Main.downloadImage(product.getImageName(), product.getImageIS()));

        //this.addToBasket.setOnMouseClicked(mouseEvent -> this.addProductToBasket(product));


    }
    public void setProductImage(Image productImage) {
        this.pictureProduct.setImage(productImage);
    }

    public void goToShop() {
        try {
            Main.changeScene("shop/homeShopClub", new HomeShopClubController(), new Object[]{facade.getClub(product.getClubId())});
        } catch (ClubNotFoundException | ProductNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addProductToBasket() {
        int quantit =1;
        Object quant = quantity.getValue();
        int m = Integer.valueOf((String) quant);

        System.out.println(Main.connectedUser.getId());
        System.out.println(Main.connectedUser.getBasket().getListProduct());
        System.out.println(Main.connectedUser.getBasket());
        System.out.println(product);



        Main.connectedUser.getBasket().addProduct(product,m);
        try {
            facade.addProduct(Main.connectedUser.getBasket(), product,m);
        } catch (SQLIntegrityConstraintViolationException | ProductNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void setParameter(Object[] params) {
        this.product = (Product) params[0];
    }
}
