package com.foirfoot.view.shop;

import com.foirfoot.model.Facade;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;

public class FormCreationProduct {
    Facade facade = new Facade();

    private File file;
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private Button addButton;
    @FXML
    private TextField nameProduct;

    @FXML
    private TextField productImageLocalPath;
    @FXML
    private TextField descProduct;
    @FXML
    private TextField price;
    @FXML
    private TextField stock;

    public void goToHomeShopClub() {
        Main.changeScene("shop/homeShopClub", new HomeShopClubController(), new Object[]{Main.connectedUser.getClub()});
    }

    @FXML
    public void openFileChooser() {
        productImageLocalPath.clear();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            printLog(productImageLocalPath, file);
        }
    }
    private void printLog(TextField textField, File file) {
        if (file == null) {
            return;
        }
        textField.setText(file.getAbsolutePath());
        this.file = file;
    }

    public void createProduct() throws SQLIntegrityConstraintViolationException, ProductNotFoundException, FileNotFoundException {
        if (nameProduct.getText().isEmpty() || descProduct.getText().isEmpty() || price.getText().isEmpty() || stock.getText().isEmpty()) {
        } else {
                //Object prix = price.getText();

            InputStream targetStream = new FileInputStream(file);


            facade.createProduct(nameProduct.getText(), descProduct.getText(), Integer.parseInt(price.getText()), stock.getText(),Main.connectedUser.getClub().getId(),productImageLocalPath.getText(), file.getName(), targetStream);
                goToHomeShopClub();


        }
    }
}
