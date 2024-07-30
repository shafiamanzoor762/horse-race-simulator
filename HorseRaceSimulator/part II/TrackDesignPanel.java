package HorseRaceSimulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrackDesignPanel extends Application {

	private Slider numberOfTracksSlider;
	private Slider trackLengthSlider;
	
	char symbol;
	String name, color;
	
    public TrackDesignPanel(char symbol, String name, String color) {
    	this.symbol=symbol;
    	this.name=name;
    	this.color=color;
	}

	@Override
    public void start(Stage primaryStage) {
        // Create UI components
        numberOfTracksSlider = new Slider(1, 5, 1);
        numberOfTracksSlider.setBlockIncrement(1);
        numberOfTracksSlider.setSnapToTicks(true);
        numberOfTracksSlider.setMajorTickUnit(1);
        numberOfTracksSlider.setMinorTickCount(0);
        numberOfTracksSlider.setShowTickLabels(true);
        numberOfTracksSlider.setShowTickMarks(true);

        trackLengthSlider = new Slider(100, 1000, 100);
        trackLengthSlider.setBlockIncrement(100);
        trackLengthSlider.setSnapToTicks(true);
        trackLengthSlider.setMajorTickUnit(100);
        trackLengthSlider.setMinorTickCount(0);
        trackLengthSlider.setShowTickLabels(true);
        trackLengthSlider.setShowTickMarks(true);

        // Layout track design options
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Number of Tracks:"), numberOfTracksSlider);
        gridPane.addRow(1, new Label("Track Length:"), trackLengthSlider);
        
        Button startRaceButton = new Button("Start Race");
        startRaceButton.setOnAction(e -> startRace(primaryStage));

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(new Label("Design Your Track"), gridPane,startRaceButton);

        root.setStyle("-fx-background-color: #E6E6FA;");
        
        // Set up the scene
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Track Design");
        primaryStage.show();
    }
    
    private void startRace(Stage primaryStage) {
        double noOfTracks = numberOfTracksSlider.getValue();
        double trackLeng = trackLengthSlider.getValue();
        
        // Pass the customized horse data to the RaceTest class
        RaceTest raceTest = new RaceTest(symbol, name, color,noOfTracks,trackLeng);
        raceTest.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

