//package HorseRaceSimulator;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class HorseCustomizationPanel extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create UI components
//        ComboBox<String> breedComboBox = new ComboBox<>();
//        breedComboBox.getItems().addAll("Thoroughbred", "Quarter Horse", "Arabian", "Appaloosa", "Paint", "Pony");
//
//        ColorPicker coatColorPicker = new ColorPicker();
//
//        CheckBox saddleCheckBox = new CheckBox("Saddle");
//        CheckBox bridleCheckBox = new CheckBox("Bridle");
//        CheckBox horseshoesCheckBox = new CheckBox("Horseshoes");
//
//        ToggleGroup symbolGroup = new ToggleGroup();
//        RadioButton symbol1 = new RadioButton("♘");
//        symbol1.setToggleGroup(symbolGroup);
//        RadioButton symbol2 = new RadioButton("♞");
//        symbol2.setToggleGroup(symbolGroup);
//        RadioButton symbol3 = new RadioButton("♛");
//        symbol3.setToggleGroup(symbolGroup);
//        RadioButton symbol4 = new RadioButton("✨");
//        symbol4.setToggleGroup(symbolGroup);
//        RadioButton symbol5 = new RadioButton("★");
//        symbol5.setToggleGroup(symbolGroup);
//        RadioButton symbol6 = new RadioButton("⚝");
//        symbol6.setToggleGroup(symbolGroup);
//        RadioButton symbol7 = new RadioButton("⭒");
//        symbol7.setToggleGroup(symbolGroup);
//
//        TextField nameTextField = new TextField();
//
//        // Layout customization options
//        GridPane gridPane = new GridPane();
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//        gridPane.addRow(0, new Label("Breed:"), breedComboBox);
//        gridPane.addRow(1, new Label("Coat Color:"), coatColorPicker);
//        gridPane.addRow(2, new Label("Accessories:"), saddleCheckBox, bridleCheckBox, horseshoesCheckBox);
//        gridPane.addRow(3, new Label("Symbol:"), symbol1, symbol2, symbol3, symbol4, symbol5, symbol6, symbol7);
//        gridPane.addRow(4, new Label("Name:"), nameTextField);
//
//        VBox root = new VBox(10);
//        root.setPadding(new Insets(20));
//        root.getChildren().addAll(new Label("Customize Your Horse"), gridPane);
//
//        // Set up the scene
//        Scene scene = new Scene(root, 700, 400);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Horse Customization");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

package HorseRaceSimulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HorseCustomizationPanel extends Application {

    private ComboBox<String> breedComboBox;
    private ColorPicker coatColorPicker;
    private ToggleGroup symbolGroup;
    private TextField nameTextField;

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        breedComboBox = new ComboBox<>();
        breedComboBox.getItems().addAll("Thoroughbred", "Quarter Horse", "Arabian", "Appaloosa", "Paint", "Pony");

        coatColorPicker = new ColorPicker();

        symbolGroup = new ToggleGroup();
        RadioButton symbol1 = new RadioButton("♘");
        symbol1.setToggleGroup(symbolGroup);
        RadioButton symbol2 = new RadioButton("♞");
        symbol2.setToggleGroup(symbolGroup);
        RadioButton symbol3 = new RadioButton("♛");
        symbol3.setToggleGroup(symbolGroup);
        RadioButton symbol4 = new RadioButton("✨");
        symbol4.setToggleGroup(symbolGroup);
        RadioButton symbol5 = new RadioButton("★");
        symbol5.setToggleGroup(symbolGroup);
        RadioButton symbol6 = new RadioButton("⚝");
        symbol6.setToggleGroup(symbolGroup);
        RadioButton symbol7 = new RadioButton("⭒");
        symbol7.setToggleGroup(symbolGroup);

        nameTextField = new TextField();
        
      CheckBox saddleCheckBox = new CheckBox("Saddle");
      CheckBox bridleCheckBox = new CheckBox("Bridle");
      CheckBox horseshoesCheckBox = new CheckBox("Horseshoes");

        // Layout customization options
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Breed:"), breedComboBox);
        gridPane.addRow(1, new Label("Coat Color:"), coatColorPicker);
        gridPane.addRow(2, new Label("Symbol:"), symbol1, symbol2, symbol3, symbol4, symbol5, symbol6, symbol7);
        gridPane.addRow(3, new Label("Name:"), nameTextField);
        gridPane.addRow(4, new Label("Accessories:"), saddleCheckBox, bridleCheckBox, horseshoesCheckBox);

        Button startRaceButton = new Button("Customize Horse");
        startRaceButton.setOnAction(e -> TrackDesign(primaryStage));

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(new Label("Customize Your Horse"), gridPane, startRaceButton);

        root.setStyle("-fx-background-color: #E6E6FA;");
        
        // Set up the scene
        Scene scene = new Scene(root, 600, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Customization");
        primaryStage.show();
    }

    private void TrackDesign(Stage primaryStage) {
        char symbol = getSelectedSymbol();
        String name = nameTextField.getText();
        String color = coatColorPicker.getValue().toString();

        TrackDesignPanel trackdesing=new TrackDesignPanel(symbol, name, color);
        trackdesing.start(primaryStage);
    }

    private char getSelectedSymbol() {
        RadioButton selectedSymbol = (RadioButton) symbolGroup.getSelectedToggle();
        return selectedSymbol.getText().charAt(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

