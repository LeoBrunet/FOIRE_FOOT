package com.foirfoot.view;

import com.foirfoot.model.shop.Product;
import com.foirfoot.model.user.User;
import com.foirfoot.view.club.ClubController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class Main extends Application {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    private static Stage stg; //fake stage

    public static User connectedUser;
    public static Product selectedProduct;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(true);
        primaryStage.setTitle("Foir'Foot : login");

        InputStream path_icon = Main.class.getResourceAsStream("assets/images/ballon.png");
        if (path_icon != null) {
            primaryStage.getIcons().add(new Image(path_icon));
        }

        System.out.println(getClass());
        System.out.println(getClass().getResource(""));
        System.out.println(getClass().getResource("user"));
        System.out.println(getClass().getResource("user/login.fxml"));
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

    public static void changeScene(String fxml, Controller controller, Object[] params) {
        fxml = fxml + ".fxml";
        Parent pane = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(fxml)));
            System.out.println(Main.class.getResource(fxml));
            controller.setParameter(params);
            fxmlLoader.setController(controller);
            pane = fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("File unreachable : " + fxml);
            e.printStackTrace();
        }
        stg.getScene().setRoot(pane);
        stg.setTitle(stg.getTitle().split(" :")[0] + " : " + fxml.split("/")[1].replace(".fxml", ""));


    }

    public static Image downloadImage(String imageName, InputStream is) {
        //TODO Delete files
        File file = new File(System.getProperty("user.dir") + "/main/java/com/foirfoot/view/assets/images/temp/" + imageName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                copyInputStreamToFile(is, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Image(file.toURI().toString());
    }

    public static boolean isClubCreatorOf(int clubCreatorId) {
        return clubCreatorId == Main.connectedUser.getId();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }
}