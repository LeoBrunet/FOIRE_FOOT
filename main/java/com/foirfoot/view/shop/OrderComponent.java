package com.foirfoot.view.shop;

import com.foirfoot.view.Main;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

public class OrderComponent extends AnchorPane {
    private Label nbProducts;
    private Label total;

    //private javafx.scene.control.Button view;

    public OrderComponent() {
        this.setPrefHeight(100.0);
        this.setPrefWidth(580.0);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        setAnchorHBox(hBox);
        getChildren().add(hBox);

        this.total = new Label();
        setAnchorPrice(this.total);
        getChildren().add(this.total);

        this.nbProducts = new Label();
        setAnchorPrice(this.nbProducts);
        getChildren().add(this.nbProducts);





        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(vBox);

        hBox.getChildren().add(this.nbProducts);



        /*view = new javafx.scene.control.Button("View");
        hBox.getChildren().add(view);*/

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
    public void setTotal(String total) {
        this.total.setText(total);
    }
    public void setNbProducts(String nbProducts) {
        this.nbProducts.setText(nbProducts);
    }
    public void goToShop() {

        Main.changeScene("shop/homeShopClub");

    }

}
