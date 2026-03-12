import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FIleIO {
    /**
     * Reads all lines from a file and returns them as an ArrayList of strings.
     *
     * @param fileName The path to the file to be read.
     * @return An ArrayList containing each line of the file.
     */
    public static ArrayList<String> readFileData(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }
    
    /**
     * Writes an ArrayList of strings to a file, each string on a new line.
     *
     * @param fileName The path to the file to be written.
     * @param data The ArrayList of strings to write to the file.
     */
    public static void writeFileData(String fileName, ArrayList<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
