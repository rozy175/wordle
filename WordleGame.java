import game.GameSession;
import io.WordFileHandler;
import io.StatsFileHandler;
import model.PlayerGameResult;

import java.util.List;
import java.util.Scanner;

public class WordleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> wordList = WordFileHandler.loadWords("wordle-words.txt");
        if (wordList.isEmpty()) {
            System.out.println("Word list is missing or empty. Cannot start game.");
            System.out.println("Press Enter to exit...");
            scanner.nextLine();
            return;
        }

        if (args.length != 1) {
            System.out.println("Please provide a number as command line argument");
            System.out.println("Press Enter to exit...");
            scanner.nextLine();
            return;
        }

        int index;
        try {
            index = Integer.parseInt(args[0]);
            if (index < 0 || index >= wordList.size()) {
                System.out.println("Invalid command-line argument. Please launch with a valid number");
                System.out.println("Press Enter to exit...");
                scanner.nextLine();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid command-line argument. Please launch with a valid number");
            System.out.println("Press Enter to exit...");
            scanner.nextLine();
            return;
        }

        String secretWord = wordList.get(index);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        System.out.println("Welcome to Wordle! Guess the 5-letter word.");

        GameSession session = new GameSession(secretWord, wordList, scanner);
        PlayerGameResult result = session.play(username);

        StatsFileHandler.saveResult("stats.csv", result);

        System.out.print("Do you want to see your stats? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            StatsFileHandler.displayStats("stats.csv", username);
        }

        System.out.println("Press Enter to exit...");
        scanner.nextLine();
        scanner.close();
    }
}
