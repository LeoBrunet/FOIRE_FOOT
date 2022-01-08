package com.foirfoot.view.shop;

import com.foirfoot.view.Main;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BasketProductComponent extends AnchorPane {
    private Label productName;
    private Label productPrice;
    private Label quantity;

    private ImageView productImage;
    private MenuButton productQuantity;

    public BasketProductComponent() {
        this.setPrefHeight(100.0);
        this.setPrefWidth(580.0);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        setAnchorHBox(hBox);
        getChildren().add(hBox);

        this.productPrice = new Label();
        setAnchorPrice(this.productPrice);
        getChildren().add(this.productPrice);

        this.productImage = new ImageView();
        productImage.setFitHeight(50.0);
        productImage.setPreserveRatio(true);

        hBox.getChildren().add(this.productImage);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(vBox);

        this.productName = new Label();
        vBox.getChildren().add(this.productName);

        this.quantity = new Label();
        vBox.getChildren().add(this.quantity);

        this.productQuantity = new MenuButton();
        vBox.getChildren().add(this.productQuantity);

    }

    private void setAnchorHBox(Node node) {
        setBottomAnchor(node, 0.0);
        setLeftAnchor(node, 10.0);
        setRightAnchor(node, 0.0);
        setTopAnchor(node, 0.0);
    }

    private void setAnchorPrice (Node node) {
        setBottomAnchor(node, 0.0);
        setRightAnchor(node, 10.0);
        setTopAnchor(node, 0.0);
    }
    public void setProductImage(Image productImage) {
        this.productImage.setImage(productImage);
    }
    public void setProductName(String productName) {
        this.productName.setText(productName);
    }
    public void setProductPrice(String productPrice) {
        this.productPrice.setText(productPrice);
    }
    public void setProductQuantity(int quantity) {
        this.quantity.setText(Integer.toString(quantity));
    }
    public void goToShop() {

        Main.changeScene("shop/homeShopClub");

    }
}
