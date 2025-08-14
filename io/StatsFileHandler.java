package io;

import model.PlayerGameResult;

import java.io.*;
import java.util.*;

public class StatsFileHandler {

    public static void saveResult(String filename, PlayerGameResult result) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(result.getUsername() + "," +
                         result.getSecretWord() + "," +
                         result.getAttempts() + "," +
                         result.getOutcome() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving stats: " + e.getMessage());
        }
    }

    public static void displayStats(String filename, String username) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No stats available.");
            return;
        }

        int gamesPlayed = 0;
        int gamesWon = 0;
        int totalAttempts = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length != 4) continue;

                String user = parts[0].trim();
                if (!user.equals(username)) continue;

                gamesPlayed++;
                int attempts = Integer.parseInt(parts[2].trim());
                String outcome = parts[3].trim();

                totalAttempts += attempts;
                if (outcome.equals("win")) {
                    gamesWon++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading stats: " + e.getMessage());
            return;
        }

        if (gamesPlayed == 0) {
            System.out.println("No stats found for " + username + ".");
            return;
        }

        double averageAttempts = (double) totalAttempts / gamesPlayed;

        System.out.println("Stats for " + username + ":");
        System.out.println("Games played: " + gamesPlayed);
        System.out.println("Games won: " + gamesWon);
        System.out.printf("Average attempts per game: %.1f\n", averageAttempts);
    }
}
