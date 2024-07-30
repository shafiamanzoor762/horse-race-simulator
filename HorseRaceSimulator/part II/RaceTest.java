package HorseRaceSimulator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RaceTest extends Application {

    private final int raceLength = 50;
    private Horse[] horses = new Horse[3];
    private Label[] horseLabels = new Label[3];
    private Timeline raceTimeline;

    private char symbol;
    private String name;
    private String color;
    
    private double noOfTracks;
    private double tracksLeng;

    private Label startLabel;


    public RaceTest(char symbol, String name, String color, double noOfTracks, double trackLeng) {
    	this.symbol = symbol;
        this.name = name;
        this.color = color;
        this.noOfTracks=noOfTracks;
        this.tracksLeng=trackLeng;
	}

	@Override
    public void start(Stage primaryStage) {
        GridPane raceTrack = new GridPane();
        raceTrack.setHgap(noOfTracks);
        raceTrack.setVgap(tracksLeng/10);
        
        // Initialize horses with customized data
        horses[0] = new Horse(symbol, name, 0.8);
        horses[1] = new Horse('♞', "KOKOMO", 0.6);
        horses[2] = new Horse('♛', "EL JEFE", 0.4);

        // Create labels for horses
        for (int i = 0; i < 3; i++) {
            horseLabels[i] = new Label(Character.toString(horses[i].getSymbol()));
            horseLabels[i].setAlignment(Pos.CENTER);
            if(i==0)
            	horseLabels[i].setStyle("-fx-font-size: 18;-fx-text-fill: "+color.substring(2) +";");
            else
            horseLabels[i].setStyle("-fx-font-size: 18;-fx-text-fill: #008CBA;");
            raceTrack.add(horseLabels[i], 0, i);
        }

        VBox root = new VBox(20);

        raceTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> moveHorses(root)));
        raceTimeline.setCycleCount(Timeline.INDEFINITE);

        root.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Horse Race Simulation");
        titleLabel.setStyle("-fx-font-size: 18; -fx-text-fill: purple; -fx-font-weight: bold;");
        startLabel = new Label("Click Start to begin the race");
        startLabel.setStyle("-fx-font-size: 16;");
        HBox startButton = new HBox();
        startButton.setAlignment(Pos.CENTER);
        Label startButtonLabel = new Label("Start");
        startButtonLabel.setStyle("-fx-font-size: 18;-fx-background-color: #4CAF50; -fx-text-fill: white;");
        final VBox finalRoot = root;
        startButtonLabel.setOnMouseClicked(event -> startRace(finalRoot));
        startButton.getChildren().add(startButtonLabel);
        
        HBox statisticButton = new HBox();
        statisticButton.setAlignment(Pos.BOTTOM_LEFT);
        Label statisticLabel = new Label("Show Statistic");
        statisticLabel.setStyle("-fx-font-size: 18;-fx-background-color: #f44336; -fx-text-fill: white;");
        statisticLabel.setOnMouseClicked(event -> ShowRaceStatistic(primaryStage));
        statisticButton.getChildren().add(statisticLabel);

        root.getChildren().addAll(titleLabel, raceTrack, startLabel, startButton,statisticButton);

        root.setStyle("-fx-background-color: #E6E6FA;");
        
        Scene scene = new Scene(root, 550, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Race");
        primaryStage.show();
    }

	private void startRace(VBox root) {
        raceTimeline.play();
        raceTimeline.setOnFinished(event -> moveHorses(root));
        startLabel.setText("Winner will be...");
        startLabel.setStyle("-fx-font-size: 20;");
    }

    private void moveHorses(VBox root) {
        for (int i = 0; i < 3; i++) {
            if (!horses[i].hasFallen() && horses[i].getDistanceTravelled() < raceLength) {
                // Move the horse based on its confidence level
                if (Math.random() < horses[i].getConfidence()) {
                    horses[i].moveForward();
                }
                updateRaceTrack();
            }
            if (horses[i].getDistanceTravelled() >= raceLength) {
                raceTimeline.stop();
                String winnerName = horses[i].getName();
                Label winnerLabel = new Label(winnerName + " wins!");
                winnerLabel.setStyle("-fx-font-size: 20;-fx-text-fill: #f44336;");
                root.getChildren().add(winnerLabel);
                break;
            }
        }
    }

    private void updateRaceTrack() {
        for (int i = 0; i < 3; i++) {
            int distance = horses[i].getDistanceTravelled();
            StringBuilder track = new StringBuilder("\t|");
            for (int j = 0; j < raceLength; j++) {
                if (j == distance) {
                    track.append(horses[i].getSymbol());
                } else {
                    track.append(" ");
                }
            }
            track.append("\t|  "+horses[i].getName());
            horseLabels[i].setText(track.toString());
        }
    }
    
    private Object ShowRaceStatistic(Stage primaryStage) {
        
        HorseRaceStatistics statistics = new HorseRaceStatistics(horses);
        statistics.start(primaryStage);
		return null;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
