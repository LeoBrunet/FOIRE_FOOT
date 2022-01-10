package com.foirfoot.view.results;

import com.foirfoot.model.Facade;
import com.foirfoot.model.result.Result;
import com.foirfoot.model.team.Team;
import com.foirfoot.view.Controller;
import com.foirfoot.view.Main;
import com.foirfoot.view.team.TeamController;
import exceptions.ProductNotFoundException;
import exceptions.TeamNotFoundException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.swing.text.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ResultsController extends Controller {
    private Team team;
    private final Facade facade = new Facade();
    List<Result> resultsFacade = new ArrayList();

    @FXML
    private Text home_team;
    @FXML
    private Text score_ht;
    @FXML
    private Text outside_team;
    @FXML
    private Text score_ot;
    @FXML
    private Text text;
    @FXML
    private VBox results;

    public void initialize() throws TeamNotFoundException, ProductNotFoundException {
        System.out.println("initialize()");
        resultsFacade = facade.getResults(team.getId());
        Collections.reverse(resultsFacade);
        //System.out.println(facade.getResults(team.getId()));
        text.setText("Latest results - " + team.getType()+ " - "+ team.getCategory() + " - "+ team.getClub().getName());
        for (Result r : resultsFacade) {
            System.out.println("------");
            System.out.println(r.getHomeTeam());
            System.out.println(r.getOutsideTeam());
            HBox resultBox = new HBox();
            resultBox.setAlignment(Pos.TOP_CENTER);
            resultBox.setStyle("-fx-border-width: 2; -fx-border-style: solid; -fx-border-radius: 4;");
            resultBox.setPadding(new Insets(5,0,5,0));
            VBox.setMargin(resultBox, new Insets(7,0,7,0));
            resultBox.setPrefSize(100, 100);
            if(team.getId() == r.getHomeTeam().getId()){
                if(r.getScoreHT() > r.getScoreOT()){
                    resultBox.setStyle("-fx-background-color: green; -fx-border-radius: 4;");
                }else if(r.getScoreHT() < r.getScoreOT()){
                    resultBox.setStyle("-fx-background-color: red; -fx-border-radius: 4; -fx-opacity: 50;");
                }
            }else {
                if(r.getScoreHT() < r.getScoreOT()){
                    resultBox.setStyle("-fx-background-color: green; -fx-border-radius: 4; -fx-opacity: 50;");
                }else if(r.getScoreHT() > r.getScoreOT()){
                    resultBox.setStyle("-fx-background-color: red; -fx-border-radius: 4; -fx-opacity: 50;");
                }
            }

            HBox imgHTBox = new HBox();
            imgHTBox.setAlignment(Pos.TOP_CENTER);
            imgHTBox.setPrefSize(100, 200);
            imgHTBox.setPadding(new Insets(8,0,0,0));

            File file = new File(System.getProperty("user.dir") + "/main/java/com/foirfoot/view/assets/images/temp/" + r.getHomeTeam().getClub().getImageName());
            ImageView imgHTView = new ImageView(new Image(file.toURI().toString()));
            imgHTView.setFitHeight(75);
            imgHTView.setFitWidth(75);
            imgHTView.setPreserveRatio(true);
            imgHTBox.getChildren().add(imgHTView);


            VBox scoreHT = new VBox();
            scoreHT.setPrefSize(100, 138);
            scoreHT.setPadding(new Insets(25,0,0,0));

            HBox score1 = new HBox();
            score1.setAlignment(Pos.TOP_CENTER);
            score1.setPrefSize(44,136);

            Text nameHT = new Text(r.getHomeTeam().getClub().getName());
            //HBox.setMargin(nameHT, new Insets(0, 30, 0, 0));
            nameHT.setFont(new Font("System Bold", 25));
            nameHT.setStrokeWidth(0);
            nameHT.setStrokeType(StrokeType.OUTSIDE);


            /*Text scoreHTtext = new Text(Integer.toString(r.getScoreHT()));
            //scoreHTtext.setTextAlignment(TextAlignment.RIGHT);
            HBox.setMargin(scoreHTtext, new Insets(0, 0, 0, 90));
            scoreHTtext.setStrokeWidth(0);
            scoreHTtext.setStrokeType(StrokeType.OUTSIDE);*/

            score1.getChildren().add(nameHT);
            //score1.getChildren().add(scoreHTtext);
            scoreHT.getChildren().add(score1);

            VBox tiretVBox = new VBox();
            tiretVBox.setPrefSize(100,41);
            tiretVBox.setPadding(new Insets(25,0,0,0));

            HBox tiretHBox = new HBox();
            tiretHBox.setAlignment(Pos.TOP_CENTER);
            tiretHBox.setPrefSize(100,200);

            Text tiret = new Text(Integer.toString(r.getScoreHT()) + "  -  " + Integer.toString(r.getScoreOT()));
            tiret.setFont(new Font(20));

            tiretHBox.getChildren().add(tiret);
            tiretVBox.getChildren().add(tiretHBox);

            VBox scoreOT = new VBox();
            scoreOT.setPrefSize(100,138);
            scoreOT.setPadding(new Insets(25,0,0,0));

            HBox score2 = new HBox();
            score2.setAlignment(Pos.TOP_CENTER);
            score2.setPrefSize(44,136);

            Text nameOT = new Text( r.getOutsideTeam().getClub().getName());
            //HBox.setMargin(nameHT, new Insets(0, 0, 0, 30));
            nameOT.setFont(new Font("System Bold",25));
            nameOT.setStrokeWidth(0);
            nameOT.setStrokeType(StrokeType.OUTSIDE);

           /* Text scoreOTtext = new Text(" " + Integer.toString(r.getScoreOT()));
            HBox.setMargin(scoreOTtext, new Insets(0, 90, 0, 0));
            scoreOTtext.setStrokeWidth(0);
            scoreOTtext.setStrokeType(StrokeType.OUTSIDE);*/

            //score2.getChildren().add(scoreOTtext);
            score2.getChildren().add(nameOT);
            scoreOT.getChildren().add(score2);

            HBox imgOTBox = new HBox();
            imgOTBox.setPrefSize(100,200);
            imgOTBox.setPadding(new Insets(8,0,0,0));

            imgOTBox.setAlignment(Pos.TOP_CENTER);
            File file2 = new File(System.getProperty("user.dir") + "/main/java/com/foirfoot/view/assets/images/temp/" + r.getOutsideTeam().getClub().getImageName());
            ImageView imgOTView = new ImageView(new Image(file2.toURI().toString()));
            imgOTView.setFitHeight(75);
            imgOTView.setFitWidth(75);
            imgOTView.setPreserveRatio(true);
            imgOTBox.getChildren().add(imgOTView);

            resultBox.getChildren().addAll(imgHTBox,scoreHT,tiretVBox, scoreOT, imgOTBox);

            results.getChildren().add(resultBox);
        }
    }

    public void goToResultCreation(){
        Main.changeScene("results/resultCreation");
    }

    @Override
    public void setParameter(Object[] params) {
        this.team = (Team) params[0];
    }


    public void goToTeam(){
        Main.changeScene("team/team", new TeamController(), new Object[]{team});
    }
}
