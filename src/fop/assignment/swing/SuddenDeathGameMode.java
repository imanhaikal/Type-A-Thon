package fop.assignment.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.InstantSource;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SuddenDeathGameMode{
     public static int mistake = 0;
    public static int typeRightWord = 0;
    static String textPool11;
    public static int wordCount;
    static boolean stopWatchClock = false;
    static long startTime;
    int currentIndex = 0;
    HashMap<String , Integer> wrongWords;

    public SuddenDeathGameMode(int words) {
        this.wrongWords = UserRepository.getInstance().getCurrentUser().getWrongWords();
        this.wordCount = words;
        textPool11 = wordPool(wordCount);
        startTime = System.currentTimeMillis();
        System.out.println(textPool11);
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
        JLabel title = new JLabel("Start to type");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(Color.yellow);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        title.setBounds(0, 0, 570, 50);

        String targetText = ParagraphGenerator.generateRandomSentence(words);
        issue1.setText(parseHtml(targetText, 0));
        issue1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                char targetChar = targetText.charAt(currentIndex);

                if (typedChar == targetChar || Character.isWhitespace(targetChar)) {
                    currentIndex++;
                    typeRightWord++;
                    issue1.setText(parseHtml(targetText, currentIndex));
                    System.out.println("typeRightWord: " + typeRightWord);

                }
             /*   if (currentIndex == targetText.length()) {
                     // Handle completion of the sentence if needed
                    stopGame();
                } */
                else {
                    // Wrong key pressed, stop the game and calculate the score
                    String wrongWord = findWordAtIndex(targetText, currentIndex);
                    if(wrongWords.containsKey(wrongWord)){
                        wrongWords.put(wrongWord, wrongWords.get(wrongWord)+1);
                    }
                    else{
                        wrongWords.put(wrongWord, 1);
                    }
                    stopGame();    
                }
    
            }
       
            @Override
            public void keyPressed(KeyEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); 
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
    // "<html><body ><h1 style='color: blue; font-size: 10px;'>Hello, <i style='color: red;'>World!</i></h1></body></html>"
    
    public String parseHtml(String input,int index){
      StringBuilder html = new StringBuilder(input);
      html.insert(index, "</b>");
      html.insert(0, "<html><body ><h1 style='color: green; font-size: 13px;'><b style='color: red;'>");
      html.append("</h1></body></html>");
      //System.out.println(html.toString());
      return html.toString();
    }
    
    
    private void stopGame() {
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        // Calculate words per minute (WPM)
        //int totalWords = targetText.split("\\s+").length;
        double minutes = timeTaken / 60000.0; // Convert milliseconds to minutes
        int suddenDeathScore = (int) Math.round(typeRightWord / minutes);
        UserRepository.getInstance().getCurrentUser().setSuddenDeathScore(suddenDeathScore);
        UserRepository.getInstance().saveDataToFile();
        JOptionPane.showMessageDialog(null, "Game over! You pressed a wrong key.\nSudden Death Score: " + suddenDeathScore);
        JOptionPane.showMessageDialog(null, wrongWords.toString());
        UserRepository.getInstance().saveDataToFile();

        
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
    public static String wordPool(int length) {
        return ParagraphGenerator.generateRandomSentence(length);
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
 /*   public static void wpmCaculator(long time, double word) {
        int wpm = (int)(60/time) * (int)(word/5);
        double accuracy = typeRightWord / (double) (typeRightWord + mistake) * 100;  // Corrected accuracy calculation
        System.out.println(time);
        System.out.println(word);
        System.out.println("Words Per Minute (WPM): " + wpm);
        System.out.println("Mistake: " + mistake);
        System.out.println("Your Accuracy: " + accuracy + "%");
        UserRepository.getInstance().getCurrentUser().getWPM().add(wpm);
        UserRepository.getInstance().saveDataToFile();
    } */
}