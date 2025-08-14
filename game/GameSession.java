package game;

import model.PlayerGameResult;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class GameSession {
    private final String secret;
    private final List<String> wordList;
    private final Scanner scanner;
    private final Set<Character> remainingLetters = new HashSet<>();

    public GameSession(String secret, List<String> wordList, Scanner scanner) {
        this.secret = secret.toLowerCase();
        this.wordList = wordList;
        this.scanner = scanner;
        for (char c = 'a'; c <= 'z'; c++) {
            remainingLetters.add(c);
        }
    }

    public PlayerGameResult play(String username) {
        int attempts = 0;
        boolean won = false;

        while (attempts < 6) {
            System.out.print("Enter your guess: ");
            String rawInput = scanner.nextLine().trim();

            if (rawInput.length() != 5) {
                System.out.println("Your guess must be exactly 5 letters long.");
                continue;
            }

            if (!rawInput.matches("[a-z]{5}")) {
                System.out.println("Your guess must only contain lowercase letters.");
                continue;
            }

            String guess = rawInput.toLowerCase();

            if (!wordList.contains(guess)) {
                System.out.println("Word not in list. Please enter a valid word.");
                continue;
            }

            attempts++;
            String feedback = FeedbackFormatter.formatFeedback(guess, secret, remainingLetters);
            System.out.println("Feedback: " + feedback);
            System.out.println("Remaining letters: " + getRemainingLetters());
            System.out.println("Attempts remaining: " + (6 - attempts));

            if (guess.equals(secret)) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                won = true;
                break;
            }
        }

        if (!won) {
            System.out.println("Game over. The correct word was: " + secret);
        }

        return new PlayerGameResult(username, secret, attempts, won ? "win" : "loss");
    }

    private String getRemainingLetters() {
        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            if (remainingLetters.contains(Character.toLowerCase(c))) {
                sb.append(c).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
