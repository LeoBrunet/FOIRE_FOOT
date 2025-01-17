package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.model.shop.Product;
import com.foirfoot.model.shop.Transaction;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class OrderSummaryController extends Controller {
    Facade facade = new Facade();
    Transaction transaction;

    @FXML
    private VBox listProducts;
    @FXML
    private Button SeeOrders;
    @FXML
    public Label address;
    @FXML
    public Label payment;
    @FXML
    public Label total;
    @FXML
    public ImageView productImage;

    @FXML
    public void initialize() throws ProductNotFoundException {
        address.setText(this.transaction.getaddress()+this.transaction.getCity()+this.transaction.getCountry());
        payment.setText((String)this.transaction.getPayment());
        total.setText(Integer.toString(this.transaction.getBasket().calculTotal()));

        List<Product> products = new ArrayList<>();
        products = this.transaction.getBasket().getListProduct();

        for (Product product : products) {
            OrderProductComponent orderProductComponent = new OrderProductComponent();
            orderProductComponent.setProductName(product.getName());
            orderProductComponent.setProductQuantity(facade.getQuantityOfProduct(product.getId()));

            orderProductComponent.setProductPrice(product.getPrice()*facade.getQuantityOfProduct(product.getId()) + "$");
            orderProductComponent.setProductImage(Main.downloadImage(product.getImageName(), product.getImageIS()));

            listProducts.getChildren().add(orderProductComponent);
        }


    }



    public void goToSeeAll() {
        System.out.println(transaction.getId());
        Main.changeScene("shop/ordersList");

    }
    @Override
    public void setParameter(Object[] params) {
        this.transaction = (Transaction) params[0];
    }
}
