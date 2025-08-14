package model;

public class PlayerGameResult {
    private final String username;
    private final String secretWord;
    private final int attempts;
    private final String outcome;

    public PlayerGameResult(String username, String secretWord, int attempts, String outcome) {
        this.username = username;
        this.secretWord = secretWord;
        this.attempts = attempts;
        this.outcome = outcome;
    }

    public String getUsername() {
        return username;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public int getAttempts() {
        return attempts;
    }

    public String getOutcome() {
        return outcome;
    }
}
