package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordFileHandler {

    public static List<String> loadWords(String filename) {
        List<String> words = new ArrayList<>();

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Word list file not found: " + filename);
            return words;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim().toLowerCase();
                if (line.length() == 5 && line.matches("[a-z]{5}")) {
                    words.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading word list: " + e.getMessage());
        }

        return words;
    }
}
