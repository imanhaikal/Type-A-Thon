/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;
/*
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author resha
 */
public class SuddenDeathGameModeOld {
/*    package fop.assignment.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SuddenDeathGameMode {
    static JTextArea youTyping;
    public static int typeRightWord = 0;
    static String textPool11;
    public static int numtest;
    static boolean stopWatchClock = false;
    static long startTime;
    static boolean errorOccurred=false;
    static boolean calculationsDone = false;

    public SuddenDeathGameMode(int words) {
        this.numtest = words;
        textPool11 = wordPool(numtest);
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
        JLabel title = new JLabel("Start to type");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(Color.yellow);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        title.setBounds(0, 0, 570, 50);
        youTyping = new JTextArea();
        youTyping.setPreferredSize(new Dimension(570, 200));
        youTyping.setFont(new Font("Monospaced", Font.BOLD, 15));
        youTyping.setBackground(Color.gray);
        youTyping.setForeground(Color.yellow);
        youTyping.setBounds(0, 270, 570, 200);
        youTyping.setCaretColor(Color.yellow);

        youTyping.addKeyListener(new KeyListener() {
            @Override
    public void keyTyped(KeyEvent e) {
        if (!stopWatchClock) {
            stopWatchClock = true;
            startTime = System.currentTimeMillis();
        }

        // Check for errors in real-time
        String typeResult = youTyping.getText();
        int len = Math.min(typeResult.length(), textPool11.length());
        boolean wordCorrect = true;

        for (int i = 0; i < len; i++) {
            if (typeResult.charAt(i) != textPool11.charAt(i)) {
                stopWatchClock = false;
                wordCorrect = false;
                errorOccurred = true;
                endGame();
                break;
            }
        }

        if (len < textPool11.length() && textPool11.charAt(len) == ' ') {
            typeRightWord++;
        }
    }


            @Override
            public void keyPressed(KeyEvent e) {
                if (errorOccurred) {
                    e.consume();
                    youTyping.setEditable(false);
                    endGame();  // Move endGame() call to keyPressed
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not needed for this example
            }
        });

        gamemode.add(issue1);
        gamemode.add(title);
        gamemode.add(youTyping);
        gamemode.setLayout(null);
        gamemode.getContentPane().setBackground(Color.DARK_GRAY);
        gamemode.setVisible(true);
    }

    public static long stopWatch() {
        long time = System.currentTimeMillis();
        return (time - startTime) / 1000;
    }

    public static String wordPool(int length) {
        return MarkovChainSentenceGenerator.Generate(length);
    }

    public static void endGame() {
        if (!calculationsDone){
        calculationsDone = true;
        double suddenDeathScore =suddenDeathScore(stopWatch(), typeRightWord);
        UserRepository.getInstance().getCurrentUser().setSuddenDeathScore(suddenDeathScore);
        UserRepository.getInstance().saveDataToFile();
        System.out.println("SuddenDeath Score: " + suddenDeathScore);
        }
    }

    public static double suddenDeathScore(long time, int typeRightWord) {
        int seconds = (int) time;

    // Calculate sudden death score
        double minutes = seconds / 60.0;
        return typeRightWord / minutes;
        
      // UserRepository.getInstance().getCurrentUser().setSuddenDeathScore(suddenDeathScore);
      // UserRepository.getInstance().saveDataToFile();
    }
} */
}
