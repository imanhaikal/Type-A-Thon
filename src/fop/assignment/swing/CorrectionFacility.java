package fop.assignment.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CorrectionFacility {
    public static int mistake = 0;
    public static int typeRightWord = 0;
    static boolean stopWatchClock = false;
    static long startTime;
    int currentIndex = 0;
    HashMap<String, Integer> wrongWords;

    public CorrectionFacility() {
        this.wrongWords = UserRepository.getInstance().getCurrentUser().getWrongWords();

        // Retrieve most misspelled words from the user repository
        String mostMisspelledWords = getMostMisspelledWords();
        
        startTime = System.currentTimeMillis();
        System.out.println(mostMisspelledWords);

        JFrame gamemode = new JFrame();
        gamemode.setSize(600, 670);
        gamemode.setTitle("Game mode");
        gamemode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gamemode.setBackground(Color.black);                                                            
        JEditorPane issue1 = new JEditorPane("text/html", parseHtml("this is a sentence", 7));
        issue1.setEditable(false);                                                                                                                
        issue1.setForeground(Color.green);                                                                                                                                  
        issue1.setFont(new Font("Monospaced", Font.BOLD, 15));
        issue1.setBackground(Color.black);
        issue1.setOpaque(true);
        issue1.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        issue1.setBounds(0, 50, 570, 200);
        JLabel title = new JLabel("Start to type and press Complete to end");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(Color.yellow);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        title.setBounds(0, 0, 570, 50);

        issue1.setText(parseHtml(mostMisspelledWords, 0));
        issue1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                char targetChar = mostMisspelledWords.charAt(currentIndex);

                if (typedChar == targetChar || Character.isWhitespace(targetChar)) {
                    currentIndex++;
                    typeRightWord++;
                    issue1.setText(parseHtml(mostMisspelledWords, currentIndex));
                    System.out.println(currentIndex);

                    if (currentIndex == mostMisspelledWords.length()) {
                        long endTime = System.currentTimeMillis();
                        long timeTaken = endTime - startTime;

                        wpmCaculator(timeTaken, mostMisspelledWords.split("\\s+").length);
                        JOptionPane.showMessageDialog(null, wrongWords.toString());
                        UserRepository.getInstance().saveDataToFile();
                    }

                } else {
                    mistake++;
                    String word = findWordAtIndex(mostMisspelledWords, currentIndex);
                    if (wrongWords.containsKey(word)) {
                        wrongWords.put(word, wrongWords.get(word) + 1);
                    } else {
                        wrongWords.put(word, 1);
                    }

                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        gamemode.add(issue1);
        gamemode.add(title);
        gamemode.setLayout(null);
        gamemode.getContentPane().setBackground(Color.DARK_GRAY);
        gamemode.setVisible(true);
        gamemode.setFocusable(true);
        issue1.grabFocus();
    }

    public String parseHtml(String input, int index) {
        StringBuilder html = new StringBuilder(input);
        html.insert(index, "</b>");
        html.insert(0, "<html><body ><h1 style='color: green; font-size: 13px;'><b style='color: red;'>");
        html.append("</h1></body></html>");
        return html.toString();
    }

    public static String findWordAtIndex(String sentence, int index) {
        String[] words = sentence.split("\\s+");
        int currentPosition = 0;
        String currentWord = null;

        for (String word : words) {
            currentPosition += word.length();
            if (index < currentPosition) {
                currentWord = word;
                break;
            }
            currentPosition++;
        }

        return currentWord;
    }

    private String getMostMisspelledWords() {
        HashMap<String, Integer> wrongWords = UserRepository.getInstance().getCurrentUser().getWrongWords();

        List<String> misspelledWordList = new ArrayList<>(wrongWords.keySet());
        List<String> shuffledMisspelledWords = new ArrayList<>(misspelledWordList); // Make a copy

        Collections.shuffle(shuffledMisspelledWords);

        StringBuilder result = new StringBuilder();

        // Repeat the misspelled words to achieve the target word count
        while (result.toString().split("\\s+").length < 30) {
            Collections.shuffle(shuffledMisspelledWords); // Shuffle the copy
            for (String word : shuffledMisspelledWords) {
                result.append(word).append(" ");
            }
        }

        // Remove the trailing space
        if (result.length() > 1) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }

    public static void wpmCaculator(long timeTaken, double word) {
        double minutes = timeTaken / 60000.0;
        double wpmInitial = (int) (word) / minutes;
        int wpm = (int) wpmInitial;
        UserRepository.getInstance().getCurrentUser().getWPM().add(wpm);
        UserRepository.getInstance().saveDataToFile();
        double accuracy = typeRightWord / (double) (typeRightWord + mistake) * 100;
        System.out.println(timeTaken);
        System.out.println(word);
        System.out.println("Words Per Minute (WPM): " + wpm);
        System.out.println("Mistake: " + mistake);
        System.out.println("Your Accuracy: " + accuracy + "%");
        JOptionPane.showMessageDialog(null, "Congratulations! You typed the sentence correctly.\nWPM: " + wpm);
        JOptionPane.showMessageDialog(null,"Accuracy: " + typeRightWord / (double) (typeRightWord + mistake) * 100 + "%");
    }
}
