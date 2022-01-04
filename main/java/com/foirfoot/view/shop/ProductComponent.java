package com.foirfoot.view.shop;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductComponent extends AnchorPane {

    private Label productName;
    private ImageView productImageView;
    private Button view;
    private Button addToBasket;
    private Button change;
    private Button delete;


    public ProductComponent(){
        this.setPrefHeight(200.0);
        this.setPrefWidth(190.0);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        setAnchor(vBox);
        getChildren().add(vBox);

        productImageView = new ImageView();
        vBox.getChildren().add(productImageView);

        productName = new Label();
        VBox.setMargin(productName, new Insets(10, 0, 0, 0));
        vBox.getChildren().add(productName);

        HBox hBox = new HBox();
        VBox.setMargin(hBox, new Insets(10, 0, 10, 0));
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10.0);
        vBox.getChildren().add(hBox);

        view = new Button("View");
        addToBasket = new Button("Add to basket");
        hBox.getChildren().add(view);
        hBox.getChildren().add(addToBasket);

        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.TOP_CENTER);
        hBox2.setSpacing(10.0);
        vBox.getChildren().add(hBox2);

        change = new Button("Change");
        delete = new Button("Delete");
        hBox2.getChildren().add(change);
        hBox2.getChildren().add(delete);
    }

    private void setAnchor(Node node){
        setBottomAnchor(node, 0.0);
        setLeftAnchor(node, 0.0);
        setRightAnchor(node, 0.0);
        setTopAnchor(node, 0.0);
    }

    public void setProductName(String productName) {
        this.productName.setText(productName);
    }

public void setButtonViewAction(EventHandler<MouseEvent> mouseEventEventHandler){
        this.view.setOnMouseClicked(mouseEventEventHandler);
    }
}