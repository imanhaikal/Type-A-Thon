/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author resha
 */
public class QuotesGameMode {
    private static int mistake = 0;
    public static int typeRightWord = 0;
    static String textPool11;
    
    static boolean stopWatchClock = false;
    static long startTime;
    int currentIndex = 0;
    JFrame gamemode = new JFrame();
    HashMap<String , Integer> wrongWords;
    boolean[] mistakes;
    String targetText;
    
    public QuotesGameMode() {
        this.wrongWords = UserRepository.getInstance().getCurrentUser().getWrongWords();
        
        
        textPool11 = wordPool();
        targetText = QuotesGenerator.generateRandomQuotes(50);
        mistakes = new boolean[targetText.length()];
        startTime = System.currentTimeMillis();
        System.out.println(textPool11);
       
        gamemode.setSize(600, 670);
        gamemode.setTitle("Game mode");
        gamemode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gamemode.setBackground(Color.black);
        JEditorPane screenText = new JEditorPane("text/html", " ");
        screenText.setEditable(false);
        screenText.setForeground(Color.green);
        screenText.setFont(new Font("Monospaced", Font.BOLD, 15));
        screenText.setBackground(Color.black);
        screenText.setOpaque(true);
        screenText.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        screenText.setBounds(0, 50, 570, 200);
        ImageIcon icon = new ImageIcon("arrow.png");
        JButton BackButton = new JButton();
        int newWidth = 30;  
        int newHeight = 30;
        Image resizedImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        gamemode.setLocationRelativeTo(null);

        BackButton.setBounds(10, 10, newWidth, newHeight);
        BackButton.setOpaque(true);
        BackButton.setFont(new Font("Monospaced", Font.BOLD, 15));
        BackButton.setForeground(Color.yellow);
        BackButton.setBackground(Color.black);
        BackButton.setHorizontalAlignment(JButton.CENTER);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gamemode.setVisible(false);
            	ButtonSet t1 = new ButtonSet();
            	t1.buttonmode.setVisible(true);
            };
            
        });
        BackButton.setIcon(resizedIcon);
        BackButton.setBorderPainted(false);
        JLabel title = new JLabel("Start to type and press Complete to end");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(Color.yellow);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        title.setBounds(0, 0, 570, 50);

        
        screenText.setText(parseHtml(targetText, 0));
        screenText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                char targetChar = targetText.charAt(currentIndex);

                if (typedChar == targetChar) {
                    currentIndex++;
                    typeRightWord++;
                    screenText.setText(parseHtml(targetText, currentIndex));
                    System.out.println(currentIndex);
                    
                    
                
                }else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    // Handle backspace
                    if (currentIndex > 0) {
                        currentIndex--;
                        // Clear the mistake for the previous character
                        if(mistakes[currentIndex]) mistake--;
                        mistakes[currentIndex] = false;
                        screenText.setText(parseHtml(targetText, currentIndex));
                    }
                }else {
                    mistake++;
                    System.out.println("Mistake");
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
                if (currentIndex == targetText.length()-1) {
                        long endTime = System.currentTimeMillis();
                        long timeTaken = endTime - startTime;
                        
                        wpmCaculator(timeTaken, wordPool().length());
                        // Calculate words per minute (WPM)
                 /*       int totalWords = targetText.split("\\s+").length;
                        double minutes = timeTaken / 60000.0; // Convert milliseconds to minutes
                        int wpm = (int) Math.round(totalWords / minutes); */

                        
//                        JOptionPane.showMessageDialog(null,wrongWords.toString());
                        UserRepository.getInstance().saveDataToFile();
                    //    JOptionPane.showMessageDialog(null, "Accuracy: " +typeRightWord / (double) (typeRightWord + mistake) * 100 +"%"); 
                    }    
            }
       
            @Override
            public void keyPressed(KeyEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); 
            }

            @Override
            public void keyReleased(KeyEvent e) {
               //throw new UnsupportedOperationException("Not supported yet."); 
            }
        });
            
        
      
        
        gamemode.add(screenText);
        gamemode.add(title);
        gamemode.add(BackButton);
        gamemode.setLayout(null);
        gamemode.getContentPane().setBackground(Color.DARK_GRAY);
        gamemode.setVisible(true); 
        gamemode.setFocusable(true);
        screenText.grabFocus();
    }
    // "<html><body ><h1 style='color: blue; font-size: 10px;'>Hello, <i style='color: red;'>World!</i></h1></body></html>"
    // "<html><h1 style='color: white; font-size: 10px;'> <i style='color: green;'>He<i style='color: red;'>l</i>l</i>o World!</h1></html>"
    
    public String parseHtml(String input,int index){
        StringBuilder html = new StringBuilder("<html><h1 style='color: white; font-size: 13px;'> <i style='color: green;'>");
        for(int i=0; i<input.length();i++){
            if(mistakes[i] == true){
                html.append("<i style='color: red;'>").append(input.charAt(i)).append("</i>");
            }else{
                html.append(input.charAt(i));
            }
            
            if(i== index-1){
                html.append("</i>");
            }
        }

        html.append("</h1></html>");
        System.out.println(html);
        return html.toString();
    }
    
    public static String findWordAtIndex(String sentence, int index) {
        // Split the sentence into words
        String[] words = sentence.split("\\s+");

        // Initialize variables to keep track of the current position and word
        int currentPosition = 0;
        String currentWord = null;

        // Iterate through each word
        for (String word : words) {
            // Update the current position based on the length of the current word
            currentPosition += word.length();

            // Check if the index is within the current word
            if (index < currentPosition) {
                currentWord = word;
                break;
            }

            // Add 1 for the space between words
            currentPosition++;
        }

        return currentWord;
    }
    
    public static long stopWatch() {
        long time = System.currentTimeMillis();
        return (time - startTime) / 1000;
    }
/*
    public static String wordPool(int length) {
        String[] wordPool = {"the", "and", "is", "in", "it", "you", "to", "that", "was", "for",
                "on", "are", "with", "as", "at", "be", "this", "have", "from", "or",
                "but", "not", "by", "an", "if", "they", "we", "can", "do", "more",
                "has", "get", "when", "will", "some", "into", "up", "its", "out",
                "so", "about", "who", "which", "go", "me", "one", "see", "make",
                "know", "time", "take", "no", "just", "people", "year", "your",
                "good", "could", "them", "than", "now", "look", "only", "come",
                "its", "over", "think", "also", "back", "after", "use", "two",
                "how", "our", "work", "first", "well", "way", "even", "new", "want",
                "because", "any", "these", "give", "day", "most", "us"};
        StringBuilder textPool = new StringBuilder();
        for (int i = 0; i < length; i++) {
            textPool.append(wordPool[(int) (Math.random() * wordPool.length)]).append(" ");
        }
        return textPool.toString();
    }
*/
    public static String wordPool() {
            return QuotesGenerator.generateRandomQuotes(50);
    }
  /*  public static void typingGameWord(String myTextPool) {
        mistake = 0;
        typeRightWord = 0;
        System.out.println("Start to type and press Complete to end: ");
        String typeResult = userTypingArea.getText();

        int minLength = Math.min(typeResult.length(), myTextPool.length());
        for(int i = 0;i<minLength;i++){
            if(typeResult.charAt(i) == myTextPool.charAt(i)) {
                typeRightWord++;
            }
            else{
                mistake++;
            }
        }
    } */
    public void wpmCaculator(long timeTaken, double word) {
        System.out.println(targetText);

        HashSet<String> wordSet = new HashSet<>(Arrays.asList(targetText.split("\\s+")));
        System.out.println(wordSet);

        for (int i = 0; i < mistakes.length; i++) {
            if (mistakes[i]) {
                System.out.println(findWordAtIndex(targetText, i));
                wordSet.remove(findWordAtIndex(targetText, i));
            }
        }
        System.out.println(wordSet);

        double effectiveWordCount = wordSet.size();
        double minutes = timeTaken / 60000.0;
        double wpmInitial = effectiveWordCount / minutes;
        int wpm = (int) wpmInitial;

        double accuracy = (typeRightWord / (double) (typeRightWord + mistake)) * 100;

        // Show results in JOptionPane dialog
        String message = "Words Per Minute (WPM): " + wpm + "\n"
                + "Mistakes: " + mistake + "\n"
                + String.format("Accuracy: %.2f%%", accuracy);
        
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
    

} 