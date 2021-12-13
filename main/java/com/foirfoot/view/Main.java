package com.foirfoot.view;

import com.foirfoot.model.user.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class Main extends Application {
    private static Stage stg; //fake stage

    public static User connectedUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(true);
        primaryStage.setTitle("Foir'Foot : login");

        InputStream path_icon = Main.class.getResourceAsStream("assets/images/ballon.png");
        if (path_icon != null) {
            primaryStage.getIcons().add(new Image(path_icon));
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user/login.fxml")));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.show();
    }

    public static void changeScene(String fxml) {
        fxml = fxml + ".fxml";
        Parent pane = null;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        } catch (IOException e) {
            System.err.println("File unreachable : " + fxml);
            e.printStackTrace();
        }
        stg.getScene().setRoot(pane);
        stg.setTitle(stg.getTitle().split(" :")[0] + " : " + fxml.split("/")[1].replace(".fxml", ""));
    }


    public static void main(String[] args) {
        launch(args);
    }
}