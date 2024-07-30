package HorseRaceSimulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DemoRace extends Application {
    
	Horse[] horses=new Horse[3];
	
    @Override
    public void start(Stage primaryStage) {
    	
        horses[0] = new Horse('♘', "PIPPI LONGSTOCKING",Color.RED, 0.6,38);
        horses[1] = new Horse('♞', "KOKOMO",Color.GREEN, 0.5,32);
        horses[2] = new Horse('♛', "EL JEFE",Color.BLUE, 0.4,28);       
        
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 400);
        
        root.setStyle("-fx-background-color: #E6E6FA;");

        // Create welcome label
        Label welcomeLabel = new Label("\n\n\t Welcome to Horse Race Simulator");
        welcomeLabel.setStyle("-fx-font-size: 24; -fx-text-fill: purple; -fx-font-weight: bold;");
        
        // Create buttons
        Button horseStatsButton = new Button("Horse Race Statistics");
        horseStatsButton.setOnAction(e -> navigateToHorseRaceStatistics());
        Button horseCustomizationButton = new Button("Horse Customization");
        horseCustomizationButton.setOnAction(e -> navigateToHorseCustomization());
        Button raceTestButton = new Button("Race Test");
        raceTestButton.setOnAction(e -> navigateToRaceTest());
        Button betRaceButton = new Button("Bet Race");
        betRaceButton.setOnAction(e -> navigateToBetRace());

        // Style buttons
        horseStatsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        horseCustomizationButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white;");
        raceTestButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        betRaceButton.setStyle("-fx-background-color: purple; -fx-text-fill: white;");

        // Layout
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(horseStatsButton, horseCustomizationButton, raceTestButton,betRaceButton);
        buttonBox.setPadding(new Insets(20));

        // Add welcome label and buttons to root
        root.setTop(welcomeLabel);
        root.setCenter(buttonBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Racing Application");
        primaryStage.show();
    }

    private void navigateToHorseRaceStatistics() {
        // To navigate to HorseRaceStatistics class
        HorseRaceStatistics horseracestatistics = new HorseRaceStatistics(horses);
        horseracestatistics.start(new Stage());
        System.out.println("Navigating to Horse Race Statistics");
    }

    private void navigateToHorseCustomization() {
        // To navigate to HorseCustomization class
        HorseCustomizationPanel horsecustomizationpanel = new HorseCustomizationPanel();
        horsecustomizationpanel.start(new Stage());
        System.out.println("Navigating to Horse Customization");
    }

    private void navigateToRaceTest() {
        // To navigate to RaceTest class
        RaceTest raceTest = new RaceTest('♘', "PIPPI LONGSTOCKING",Color.RED.toString(), 2, 200);
        raceTest.start(new Stage());
        System.out.println("Navigating to Race Test");
    }
    
    private void navigateToBetRace() {
    	HorseRaceBetting betRace = new HorseRaceBetting();
        betRace.start(new Stage());
        System.out.println("Navigating to Bet Race");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
