package HorseRaceSimulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HorseRaceBetting extends Application {
    
    private Label oddsLabel;
    private TextField betAmountField;
    
    double[] currentOdds= {2.5, 3.0, 4.0};
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize UI components
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        
        Label titleLabel = new Label("Horse Race Betting");
        oddsLabel = new Label("Current Odds: ");
        
        Label betAmountLabel = new Label("Enter Bet Amount:");
        betAmountField = new TextField();
        
        Button placeBetButton = new Button("Place Bet");
        placeBetButton.setOnAction(event -> placeBet());

        root.getChildren().addAll(titleLabel, oddsLabel, betAmountLabel, betAmountField, placeBetButton);

        root.setStyle("-fx-background-color: #E6E6FA;");
        
        Scene scene = new Scene(root, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Race Betting");
        primaryStage.show();
        updateOddsLabel();
    }

    private void updateOddsLabel() {
        
        StringBuilder oddsText = new StringBuilder("Current Odds:\n");
        for (int i = 0; i < currentOdds.length; i++) {
            oddsText.append("Horse ").append(i + 1).append(": ").append(currentOdds[i]).append("\n");
        }
        oddsLabel.setText(oddsText.toString());
    }
    
    private void placeBet() {
        String betAmountText = betAmountField.getText();
        double betAmount = Double.parseDouble(betAmountText);

        String result=BetProcessor.placeBet("user1", betAmount,currentOdds , 2);
        System.out.println("Bet placed: Amount = " + betAmount+result);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

