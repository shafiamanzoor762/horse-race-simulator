package HorseRaceSimulator;

import java.util.HashMap;
import java.util.Map;

public class BetProcessor {
    private static Map<String, Double> userAccounts = new HashMap<>();
    
    static {
        userAccounts.put("user1", 1000.0);
        userAccounts.put("user2", 500.0);
    }
    
    public static String placeBet(String userId, double betAmount, double[] currentOdds, int horseIndex) {

        if (!userAccounts.containsKey(userId) || userAccounts.get(userId) < betAmount) {
            return "Insufficient balance to place the bet";
        }
        userAccounts.put(userId, userAccounts.get(userId) - betAmount);

        currentOdds[horseIndex] += 0.1;
        
        boolean win = Math.random() < 0.5;

        if (win) {
            double winnings = betAmount * currentOdds[horseIndex];
            userAccounts.put(userId, userAccounts.get(userId) + winnings);
            return "Congratulations! You won " + winnings + " units";
        } else {
            return "Sorry, you lost the bet";
        }
    }
}

