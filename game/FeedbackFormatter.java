package game;

import java.util.Set;
import java.util.HashMap;

public class FeedbackFormatter {

    public static String formatFeedback(String guess, String secret, Set<Character> remainingLetters) {
        StringBuilder feedback = new StringBuilder();
        char[] guessChars = guess.toCharArray();
        char[] secretChars = secret.toCharArray();
        String[] colors = new String[5];

        
        HashMap<Character, Integer> secretCounts = new HashMap<>();
        for (char c : secretChars) {
            secretCounts.put(c, secretCounts.getOrDefault(c, 0) + 1);
        }

        
        for (int i = 0; i < 5; i++) {
            if (guessChars[i] == secretChars[i]) {
                colors[i] = "\u001B[32m"; 
                secretCounts.put(guessChars[i], secretCounts.get(guessChars[i]) - 1);
            }
        }

        
        for (int i = 0; i < 5; i++) {
            if (colors[i] != null) continue; 

            char g = guessChars[i];
            if (secretCounts.getOrDefault(g, 0) > 0) {
                colors[i] = "\u001B[33m"; 
                secretCounts.put(g, secretCounts.get(g) - 1);
            } else {
                colors[i] = "\u001B[37m";
            }
        }


        for (int i = 0; i < 5; i++) {
            char g = guessChars[i];
            feedback.append(colors[i]).append(Character.toUpperCase(g)).append("\u001B[0m");
            if (colors[i].equals("\u001B[37m")) {
                remainingLetters.remove(g);
            }
        }

        return feedback.toString();
    }
}
