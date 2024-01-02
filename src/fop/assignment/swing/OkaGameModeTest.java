package fop.assignment.swing;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.InstantSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class OkaGameModeTest {
    
    // Static variables for tracking mistakes, correct words, and other game-related data
    private static int mistake = 0;
    public static int typeRightWord = 0;
    static String textPool11;
    public static int wordCount;
    static boolean stopWatchClock = false;
    static long startTime;
    int currentIndex = 0;
    HashMap<String, Integer> wrongWords;
    boolean[] mistakes;
    String targetText;
    private JFrame gamemode;
    
    // Constructor for the game mode
    public OkaGameModeTest(int words) {
        // Initialize game-related data
        this.wrongWords = UserRepository.getInstance().getCurrentUser().getWrongWords();
        this.wordCount = words;
        targetText = wordPool(wordCount);
        mistakes = new boolean[targetText.length()];
        startTime = System.currentTimeMillis();
        
        // Set up the JFrame for the game
        gamemode = new JFrame();
        gamemode.setSize(600, 670);
        gamemode.setTitle("Game mode");
        gamemode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gamemode.setBackground(Color.black);
        gamemode.setLocationRelativeTo(null);
        
        // Set up the text area for displaying the target text
        JEditorPane screenText = new JEditorPane("text/html", " ");
        screenText.setEditable(false);
        screenText.setForeground(Color.green);
        screenText.setFont(new Font("Monospaced", Font.BOLD, 15));
        screenText.setBackground(Color.black);
        screenText.setOpaque(true);
        screenText.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        screenText.setBounds(0, 50, 570, 200);
        
        // Set up a label for the game title
        JLabel title = new JLabel("Start to type and press Complete to end");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(Color.yellow);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        title.setBounds(0, 0, 570, 50);

        // Set the target text for the game
        screenText.setText(parseHtml(targetText, 0));
        
        // Add a KeyListener to the text area to handle user input
        screenText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                char targetChar = targetText.charAt(currentIndex);

                if (typedChar == targetChar) {
                    // Handle correct typing
                    currentIndex++;
                    typeRightWord++;
                    screenText.setText(parseHtml(targetText, currentIndex));
                    
                

                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    // Handle backspace
                    if (currentIndex > 0) {
                        currentIndex--;
                        if(mistakes[currentIndex]) mistake--;
                        mistakes[currentIndex] = false;
                        screenText.setText(parseHtml(targetText, currentIndex));
                    }
                } else {
                    // Handle mistakes
                    mistake++;
                    mistakes[currentIndex] = true;
                    currentIndex++;
                    if (currentIndex < targetText.length()) {
                        screenText.setText(parseHtml(targetText, currentIndex));
                        String word = findWordAtIndex(targetText, currentIndex);
                        if (wrongWords.containsKey(word)) {
                            wrongWords.put(word, wrongWords.get(word) + 1);
                        } else {
                            wrongWords.put(word, 1);
                        }
                    }
                }
                if (currentIndex == targetText.length()) {
                    // Game completed, calculate and display results
                    long endTime = System.currentTimeMillis();
                    long timeTaken = endTime - startTime;
                    wpmCaculator(timeTaken, wordCount);
//                        JOptionPane.showMessageDialog(null, wrongWords.toString());
                    UserRepository.getInstance().saveDataToFile();
                }   
            }
       
            @Override
            public void keyPressed(KeyEvent e) {
                // Unused method
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Unused method
            }
        });
            
        // Add components to the JFrame
        gamemode.add(screenText);
        gamemode.add(title);
        gamemode.setLayout(null);
        gamemode.getContentPane().setBackground(Color.DARK_GRAY);
        gamemode.setVisible(true); 
        gamemode.setFocusable(true);
        screenText.grabFocus();
    }
    
    // Method to generate HTML-formatted text with color-coded characters based on mistakes
    public String parseHtml(String input, int index) {
        StringBuilder html = new StringBuilder("<html><h1 style='color: white; font-size: 13px;'> <i style='color: green;'>");
        for (int i = 0; i < input.length(); i++) {
            if (mistakes[i]) {
                html.append("<i style='color: red;'>").append(input.charAt(i)).append("</i>");
            } else {
                html.append(input.charAt(i));
            }
            
            if (i == index - 1) {
                html.append("</i>");
            }
        }

        html.append("</h1></html>");
        return html.toString();
    }
    
    // Method to find the word at a given index in a sentence
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
    
    // Method to calculate Words Per Minute (WPM) and display results
    public void wpmCaculator(long timeTaken, double word) {
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(targetText.split("\\s+")));
        
        for (int i = 0; i < mistakes.length; i++) {
            if (mistakes[i]) {
                wordSet.remove(findWordAtIndex(targetText, i));
            }
        }

        double effectiveWordCount = wordSet.size();
        double minutes = timeTaken / 60000.0;
        double wpmInitial = effectiveWordCount / minutes;
        int wpm = (int) wpmInitial;

        double accuracy = (typeRightWord / (double) (typeRightWord + mistake)) * 100;

        // Show results in JOptionPane dialog
        String message = "Words Per Minute (WPM): " + wpm + "\n"
                + "Mistakes: " + mistake + "\n"
                + String.format("Accuracy: %.2f%%",accuracy);
        
        // Update user data and save to file
        UserRepository.getInstance().getCurrentUser().getWPM().add(wpm);
        UserRepository.getInstance().getCurrentUser().getAccuracy().add(accuracy);
        UserRepository.getInstance().saveDataToFile();

        AWTEventListener myListener = new AWTEventListener() {

            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof KeyEvent) {
                    ((KeyEvent) event).consume();
                }
            }
        };
        Toolkit.getDefaultToolkit().addAWTEventListener(myListener, AWTEvent.KEY_EVENT_MASK);
        JOptionPane.showMessageDialog(null, message, "WPM and Accuracy", JOptionPane.INFORMATION_MESSAGE);
        Toolkit.getDefaultToolkit().removeAWTEventListener(myListener);
        
        gamemode.dispose();
    }

    // Method to generate a random sentence based on word count
    public static String wordPool(int length) {
        return ParagraphGenerator.generateRandomSentence(length);
    }
}
