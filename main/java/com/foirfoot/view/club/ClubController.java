package com.foirfoot.view.club;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import com.foirfoot.view.team.ListTeamsController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClubController extends Controller {

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    private Club club;
    @FXML
    private Text clubName;
    @FXML
    private Text clubAddress;
    @FXML
    private Text clubPhoneNumber;
    @FXML
    private Text clubWebsite;
    @FXML
    private VBox players;
    @FXML
    private VBox coaches;
    @FXML
    private ImageView clubImageView;
    @FXML
    private ImageView editClub;

    @FXML
    public void initialize() {
        clubName.setText(this.club.getName());
        clubAddress.setText(this.club.getAddress());
        clubPhoneNumber.setText(this.club.getPhoneNumber());
        clubWebsite.setText(this.club.getWebsite());

        for (User p : club.getPlayers()) {
            players.getChildren().add(new Text(p.getFirstName() + " " + p.getName()));
        }
        for (User c : club.getCoaches()) {
            coaches.getChildren().add(new Text(c.getFirstName() + " " + c.getName()));
        }

        //TODO Delete files
        File file = new File(System.getProperty("user.dir") + "/main/java/com/foirfoot/view/assets/images/temp/" + club.getImageName());
        if (!file.exists()) {
            try {
                file.createNewFile();
                copyInputStreamToFile(club.getImageIS(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clubImageView.setImage(new Image(file.toURI().toString()));

        if (club.getId() != Main.connectedUser.getClub().getId()){
            editClub.setVisible(false);
        }
    }

    @Override
    public void setParameter(Object[] params) {
        this.club = (Club) params[0];
    }

    public void goToTeams() {
        if (Main.connectedUser.getClub().getTeams().isEmpty()) {
            Main.changeScene("team/no_team");
        } else {
            Main.changeScene("team/list_teams", new ListTeamsController(), new Object[]{club});
        }
    }

    public void goToClubModification() {
        Main.changeScene("club/clubCreationModification", new ClubCreationModificationController(), new Object[]{Main.connectedUser.getClub(), true});
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
