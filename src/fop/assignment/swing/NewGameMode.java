/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author resha
 */
public class NewGameMode {
    static JTextArea userTypingArea;
    public static int mistake = 0;
    public static int typeRightWord = 0;
    static String textPool11;
    public static int wordCount;
    static boolean stopWatchClock = false;
    static long startTime;
    
    public NewGameMode(int words) {
        this.wordCount = words;
        textPool11 = wordPool(wordCount);
        System.out.println(textPool11);
        JFrame gamemode = new JFrame();
        gamemode.setSize(570, 670);
        gamemode.setTitle("Game mode");
        gamemode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamemode.setBackground(Color.black);
        JLabel issue1 = new JLabel("<html>" + textPool11 + "</html>");
        issue1.setHorizontalAlignment(JLabel.CENTER);
        issue1.setVerticalAlignment(JLabel.TOP);
        issue1.setVerticalTextPosition(JLabel.CENTER);
        issue1.setHorizontalTextPosition(JLabel.CENTER);
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
        userTypingArea = new JTextArea();
        userTypingArea.setPreferredSize(new Dimension(570, 200));
        userTypingArea.setFont(new Font("Monospaced", Font.BOLD, 15));
        userTypingArea.setBackground(Color.gray);
        userTypingArea.setForeground(Color.yellow);
        userTypingArea.setBounds(0, 270, 570, 200);
        userTypingArea.setCaretColor(Color.yellow);

    userTypingArea.addKeyListener(new java.awt.event.KeyAdapter() {
        private boolean firstKeyPress = true;

        public void keyTyped(java.awt.event.KeyEvent evt) {
            if (firstKeyPress) {
                stopWatchClock = true;
                startTime = System.currentTimeMillis();
                firstKeyPress = false;
            }
        }
    });

        JButton submitButton = new JButton("Complete");
        submitButton.setBounds(185, 480, 200, 70);
        submitButton.setOpaque(true);
        submitButton.setFont(new Font("Monospaced", Font.BOLD, 15));
        submitButton.setForeground(Color.yellow);
        submitButton.setBackground(Color.black);
        submitButton.setHorizontalAlignment(JButton.CENTER);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopWatchClock = false;
                typingGameWord(textPool11);
                wpmCaculator(stopWatch(), typeRightWord);
               // JOptionPane.showMessageDialog("Hello");
                
            };
            
        });
        submitButton.setBorderPainted(false);

        gamemode.add(issue1);
        gamemode.add(title);
        gamemode.add(userTypingArea);
        gamemode.add(submitButton);
        gamemode.setLayout(null);
        gamemode.getContentPane().setBackground(Color.DARK_GRAY);
        gamemode.setVisible(true); 
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
    public static String wordPool(int length) {
        return MarkovChainSentenceGenerator.Generate(length);
    }
    public static void typingGameWord(String myTextPool) {
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
    }
    public static void wpmCaculator(long time, double word) {
        int wpm = (int)(60/time) * (int)(word/5);
        double accuracy = typeRightWord / (double) (typeRightWord + mistake) * 100;  // Corrected accuracy calculation
        System.out.println(time);
        System.out.println(word);
        System.out.println("Words Per Minute (WPM): " + wpm);
        System.out.println("Mistake: " + mistake);
        System.out.println("Your Accuracy: " + accuracy + "%");
        UserRepository.getInstance().getCurrentUser().getWPM().add(wpm);
        UserRepository.getInstance().saveDataToFile();
    }
    

} 

