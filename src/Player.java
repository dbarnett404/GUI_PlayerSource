import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a player with a name, high score, and the last time they played.
 */
public class Player {

    // Human-friendly date/time for UI display (e.g., 12 Mar 2026, 2:35 PM)
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a", Locale.UK);

    private String playerName; // The name of the player
    private int highScore; // The player's high score
    private LocalDateTime lastPlayed; // The last time the player played

    /**
     * Constructs a Player object with the specified name, high score, and last played time.
     *
     * @param playerName The name of the player.
     * @param highScore The player's high score.
     * @param lastPlayed The last time the player played, as a LocalDateTime object.
     */
    public Player(String playerName, int highScore, LocalDateTime lastPlayed) {
        this.playerName = playerName;
        this.highScore = highScore;
        this.lastPlayed = lastPlayed;
    }

    /**
     * Constructs a Player object from a CSV-formatted string.
     *
     * @param csvLine A string in the format "playerName,highScore,lastPlayed".
     *                The lastPlayed field should be in ISO-8601 format.
     * @throws IllegalArgumentException If the CSV line is not properly formatted, or if the highScore
     *                                  or lastPlayed values are invalid.
     */
    public Player(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("CSV line must contain exactly three parts: playerName,highScore,lastPlayed");
        }
        playerName = parts[0];
        try {
            highScore = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("High score must be a valid integer", e);
        }
        try {
            lastPlayed = LocalDateTime.parse(parts[2]);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Last played must be in ISO-8601 format", e);
        }
    }

    /**
     * Gets the name of the player.
     *
     * @return The player's name.
     */
    public String getPlayerName() { return playerName; }

    /**
     * Saves the current player's data to the "players.csv" file.
     * Appends the player's CSV representation to the end of the file.
     */
    public void savePlayerData() {
        java.util.ArrayList<String> data = FIleIO.readFileData("players.csv");
        data.add(this.CSVStr());
        FIleIO.writeFileData("players.csv", data);
    }
    /**
     * Gets the player's high score.
     *
     * @return The player's high score.
     */
    public int getHighScore() { return highScore; }

    /**
     * Gets the last time the player played.
     *
     * @return The last played time as a LocalDateTime object.
     */
    public LocalDateTime getLastPlayed() { return lastPlayed; }

    public String getLastPlayedStr() {

        return lastPlayed.format(DISPLAY_DATE_TIME_FORMATTER);
    }

    public void setLastPlayed() {
        this.lastPlayed = LocalDateTime.now();
    }

    /**
     * Converts the Player object to a CSV-formatted string.
     *
     * @return A string in the format "playerName,highScore,lastPlayed".
     */
    public String CSVStr() {
        return playerName + "," + highScore + "," + lastPlayed;
    }

    public String toString() {
        return playerName + ", high score: " + highScore + ", last played: " + getLastPlayedStr();
    }
}
