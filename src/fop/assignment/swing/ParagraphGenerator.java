package fop.assignment.swing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParagraphGenerator {

    private List<String> wordList;
    private Random random;

    public ParagraphGenerator() {
        wordList = new ArrayList<>();
        random = new Random();
    }

    public void train(String text) {
        // Split the input text into words
        String[] words = text.split("\\s+");

        // Add all words to the word list
        wordList.addAll(Arrays.asList(words));
    }

    public String generateSentence(int length) {
        StringBuilder sentence = new StringBuilder();

        for (int i = 0; i < length; i++) {
            String nextWord = generateWord();
            if (nextWord != null) {
                sentence.append(" ").append(nextWord);
            } else {
                break;  // Stop if no next word is available
            }
        }

        return sentence.toString().trim();
    }

    private String generateWord() {
        if (!wordList.isEmpty()) {
            int randomIndex = random.nextInt(wordList.size());
            return wordList.get(randomIndex);
        } else {
            return null;
        }
    }

    public static String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        }
        return content.toString();
    }

    public static String generateRandomSentence(int length) {
        ParagraphGenerator generator = new ParagraphGenerator();

        // Read training text from a file
        String filePath = "C:\\Users\\user\\Desktop\\fop\\FOP ASSIGNMENT SWING\\easywordlist.txt";
        try {
            String trainingText = readFromFile(filePath);
            generator.train(trainingText);

            // Generate a new sentence
            String generatedSentence = generator.generateSentence(length);

            if (!generatedSentence.isEmpty()) {
                return generatedSentence;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}