import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordReader {
    public static String[] loadWords(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase();
                // fixed regex and now we add to the list
                if (line.matches("[a-z]{5}")) {
                    words.add(line);
                }
            }
        }
        return words.toArray(new String[0]);
    }
}