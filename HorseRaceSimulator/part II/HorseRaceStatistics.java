package HorseRaceSimulator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HorseRaceStatistics extends Application {

    private VBox root;

    private static List<String> horseNames = new ArrayList<>();
    private static List<Double> averageSpeeds = new ArrayList<>();
    private static List<Double> winRatios = new ArrayList<>();

    public HorseRaceStatistics(Horse[] horses) {
        if (horses.length > 0) {
            for (Horse horse : horses) {
                horseNames.add(horse.getName());
                averageSpeeds.add(horse.getDistanceTravelled() * horse.getConfidence());
                winRatios.add(horse.getConfidence());
               writeHorseDataToFile(horse);
            }
           writeHorseStatisticsFile(horseNames, averageSpeeds, winRatios);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        root = new VBox(10);
        root.setPadding(new Insets(20));
        
        readHorseDataFromFile();
        
        // Create a bar chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Horse Statistics");
        xAxis.setLabel("Horse Name");
        yAxis.setLabel("Value");

        // Add data series for average speeds and win ratios
        XYChart.Series<String, Number> averageSpeedSeries = new XYChart.Series<>();
        averageSpeedSeries.setName("Average Speed (m/s)");
        XYChart.Series<String, Number> winRatioSeries = new XYChart.Series<>();
        winRatioSeries.setName("Win Ratio");

        for (int i = 0; i < horseNames.size(); i++) {
            averageSpeedSeries.getData().add(new XYChart.Data<>(horseNames.get(i), averageSpeeds.get(i)));
            winRatioSeries.getData().add(new XYChart.Data<>(horseNames.get(i), winRatios.get(i)));
        }

        // Add series to the bar chart
        barChart.getData().addAll(averageSpeedSeries, winRatioSeries);

        // Create a VBox to hold the bar chart
        VBox root = new VBox(barChart);
        root.setPadding(new Insets(20));

        root.setStyle("-fx-background-color: #E6E6FA;");
        
        // Set up the scene
        Scene scene = new Scene(root, 550, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Race Statistics");
        primaryStage.show();

    }

    public static void writeHorseDataToFile(Horse horse) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("HorseData.txt", true))) {
            // Append horse data to the file
            writer.write("Name: " + horse.getName() + "\n");
            writer.write("Symbol: " + horse.getSymbol() + "\n");
            writer.write("Distance Travelled: " + horse.getDistanceTravelled() + "\n");
            writer.write("Fallen: " + horse.hasFallen() + "\n");
            writer.write("Confidence: " + horse.getConfidence() + "\n\n");

            System.out.println("Horse statistics have been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing horse statistics to file: " + e.getMessage());
        }
    }

    // Method to write horse data to a file
    public static void writeHorseStatisticsFile(List<String> horseNames, List<Double> averageSpeeds, List<Double> winRatios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("HorseStatistics.txt"))) {
            for (int i = 0; i < horseNames.size(); i++) {
                writer.write(horseNames.get(i) + "," + averageSpeeds.get(i) + "," + winRatios.get(i) + "\n");
            }
            System.out.println("Horse data has been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing horse data to file: " + e.getMessage());
        }
    }
    
    public static void readHorseDataFromFile() {
        // Clear the lists before reading new data
        horseNames.clear();
        averageSpeeds.clear();
        winRatios.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("HorseStatistics.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double averageSpeed = Double.parseDouble(parts[1]);
                double winRatio = Double.parseDouble(parts[2]);
                
                // Add data to the lists
                horseNames.add(name);
                averageSpeeds.add(averageSpeed);
                winRatios.add(winRatio);
            }
            System.out.println("Data has been read from the file and saved into lists.");
        } catch (IOException e) {
            System.err.println("Error reading horse data from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
