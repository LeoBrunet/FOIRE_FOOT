package com.foirfoot.view.club;

import com.foirfoot.model.Facade;
import com.foirfoot.model.club.Club;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import exceptions.ProductNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;

public class ClubCreationModificationController extends Controller {

    private final FileChooser fileChooser = new FileChooser();
    private final Facade facade = new Facade();

    private Club club;
    private boolean isModification;
    @FXML
    private Text pageTitle;
    @FXML
    private TextField clubName;
    @FXML
    private TextField clubAddress;
    @FXML
    private TextField clubPhoneNumber;
    @FXML
    private TextField clubWebsite;
    @FXML
    private TextField clubImageLocalPath;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Text result;
    @FXML
    private Button button;
    private File file;

    @FXML
    public void initialize() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        if (isModification) {
            this.clubName.setText(club.getName());
            this.clubAddress.setText(club.getAddress());
            this.clubPhoneNumber.setText(club.getPhoneNumber());
            this.clubWebsite.setText(club.getWebsite());
            this.clubImageLocalPath.setText(club.getImageName());
            this.pageTitle.setText("Club modification.");
            this.button.setText("Modify club");
            this.button.setOnMouseClicked(mouseEvent -> this.modifyClub());
        }
    }

    @FXML
    public void openFileChooser() {
        clubImageLocalPath.clear();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            printLog(clubImageLocalPath, file);
        }
    }

    @FXML
    public void createClub() {
        result.setText("Club being created...");
        if (verifiedEntries()) {
            try {
                InputStream targetStream = new FileInputStream(file);
                Club club = facade.createClub(clubName.getText(), clubAddress.getText(), clubPhoneNumber.getText(), clubWebsite.getText(), Main.connectedUser, clubImageLocalPath.getText(), file.getName(), targetStream);
                goToClub();
            } catch (SQLIntegrityConstraintViolationException | FileNotFoundException e) {
                result.setStyle("-fx-text-fill: #e10000");
                result.setText("File not found");
                e.printStackTrace();
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void modifyClub() {
        result.setText("Club being modified...");
        if (verifiedEntries()) {
            try {
                if (!clubImageLocalPath.getText().equals(this.club.getImageName())){
                    InputStream targetStream = new FileInputStream(file);
                    facade.updateClubAndClubImage(clubName.getText(), clubAddress.getText(), clubPhoneNumber.getText(), clubWebsite.getText(), Main.connectedUser, clubImageLocalPath.getText(), file.getName(), targetStream, this.club);
                } else {
                    facade.updateClub(clubName.getText(), clubAddress.getText(), clubPhoneNumber.getText(), clubWebsite.getText(), Main.connectedUser, this.club);
                }
                goToClub();
            } catch (FileNotFoundException | SQLIntegrityConstraintViolationException e) {
                result.setStyle("-fx-text-fill: #e10000");
                result.setText("File not found");
                e.printStackTrace();
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verifiedEntries() {
        if (clubName.getText().isEmpty() || clubAddress.getText().isEmpty() || clubPhoneNumber.getText().isEmpty() || clubWebsite.getText().isEmpty() || clubImageLocalPath.getText().isEmpty()) {
            result.setStyle("-fx-text-fill: #e10000");
            result.setText("Please enter your data");
            return false;
        }
        return true;
    }

    private void printLog(TextField textField, File file) {
        if (file == null) {
            return;
        }
        textField.setText(file.getAbsolutePath());
        this.file = file;
    }

    public void goToClub() {
        if (Main.connectedUser.getClub() == null) {
            Main.changeScene("club/no_club");
        } else {
            Main.changeScene("club/club", new ClubController(), new Object[]{Main.connectedUser.getClub()});
        }
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
        this.isModification = (boolean) params[1];
    }
}
