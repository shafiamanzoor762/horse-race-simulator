package HorseRaceSimulator;

import java.awt.Color;

public class Horse {
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean fallen;
    private double confidence;
    
    private javafx.scene.paint.Color color;

    public Horse(char symbol, String name, double confidence) {
        this.symbol = symbol;
        this.name = name;
        this.confidence = confidence;
        this.distanceTravelled = 0;
        this.fallen = false;
    }

	public Horse(char symbol2, String name2, javafx.scene.paint.Color coatColor) {
		symbol = symbol2;
        name = name2;
        distanceTravelled = 0;
        fallen = false;
        color = coatColor;
	}

	public Horse(char symbol, String name,javafx.scene.paint.Color coatColor, double conf, int distance) {
		this.symbol = symbol;
        this.name = name;
        distanceTravelled = distance;
        fallen = false;
        confidence = conf;
        color = coatColor;
	}


	public void fall() {
        fallen = true;
    }

    public double getConfidence() {
        return confidence;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void goBackToStart() {
        distanceTravelled = 0;
        fallen = false;
    }

    public boolean hasFallen() {
        return fallen;
    }

    public void moveForward() {
        if (!fallen) {
            distanceTravelled++;
        }
    }

    public void setConfidence(double newConfidence) {
        if (newConfidence >= 0 && newConfidence <= 1) {
            confidence = newConfidence;
        } else {
            System.out.println("Confidence rating must be between 0 and 1");
        }
    }

    public void setSymbol(char newSymbol) {
        symbol = newSymbol;
    }
}
