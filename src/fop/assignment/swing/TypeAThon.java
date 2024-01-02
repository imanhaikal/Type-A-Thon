/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TypeAThon extends JFrame implements KeyListener, ActionListener{
    private JTextArea textArea;
    private JTextField inputField;
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;
    private int correctChars;
    private int totalChars;
    private int mistakes;

    private static final String[] WORD_POOL = {"when", "there", "look", "where", "feel", "consider", "try", "action"};

    public TypeAThon() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Type-A-Thon Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        inputField = new JTextField(30);
        inputField.addKeyListener(this);
        add(inputField, BorderLayout.SOUTH);

        timerLabel = new JLabel("Timer: 30s");
        add(timerLabel, BorderLayout.NORTH);

        timer = new Timer(1000, this);

        setRandomText();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setRandomText() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {  // Generating a large text
            builder.append(WORD_POOL[random.nextInt(WORD_POOL.length)]).append(" ");
        }

        textArea.setText(builder.toString());
    }

    private void startGame() {
        startTime = System.currentTimeMillis();
        timer.start();
    }

    private void endGame() {
        timer.stop();
        inputField.setEnabled(false);
        calculateScore();
    }

    private void calculateScore() {
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        int wordsTyped = correctChars / 5;
        double minutes = elapsedTime / 60000.0;
        int wpm = (int) (wordsTyped / minutes);

        double accuracy = ((double) correctChars / totalChars) * 100;

        JOptionPane.showMessageDialog(this, "Game Over!\nWPM: " + wpm + "\nAccuracy: " + accuracy + "%");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!timer.isRunning()) {
            startGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (timer.isRunning()) {
            checkInput();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTimer();
    }

    private void updateTimer() {
        int remainingSeconds = (int) Math.max(0, 30 - (System.currentTimeMillis() - startTime) / 1000);
        timerLabel.setText("Timer: " + remainingSeconds + "s");

        if (remainingSeconds == 0) {
            endGame();
        }
    }

    private void checkInput() {
        String targetText = textArea.getText();
        String userInput = inputField.getText();
        int minLength = Math.min(targetText.length(), userInput.length());

        correctChars = 0;
        totalChars = userInput.length();
        mistakes = 0;

        for (int i = 0; i < minLength; i++) {
            if (userInput.charAt(i) == targetText.charAt(i)) {
                correctChars++;
            } else {
                inputField.setForeground(Color.RED); // Highlighting wrong characters
                mistakes++;
            }
        }

        // Highlighting remaining characters
        inputField.setForeground(mistakes == 0 ? Color.GREEN : Color.RED);

        if (minLength == targetText.length() && mistakes == 0) {
            setRandomText();
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TypeAThon::new);
    }
}
