package fop.assignment.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TypingGame extends JFrame {

    private JPanel panel;
    private JLabel[] labels;
    private String targetText = "Type this sentence accurately.";
    private int currentIndex = 0;
    private long startTime;
    private UserRepository userRepository;
    private MainMenu mainMenu;

    public TypingGame() {
        this.mainMenu = mainMenu;
        userRepository = UserRepository.getInstance();
        setTitle("Classic Mode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLayout(new FlowLayout());

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setFocusable(true);

        labels = new JLabel[targetText.length()];
        for (int i = 0; i < targetText.length(); i++) {
            labels[i] = new JLabel(String.valueOf(targetText.charAt(i)));
            labels[i].setFont(new Font("Arial", Font.PLAIN, 18));
            panel.add(labels[i]);
        }

        add(panel);

        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                char targetChar = targetText.charAt(currentIndex);

                if (typedChar == targetChar || Character.isWhitespace(targetChar)) {
                    labels[currentIndex].setForeground(Color.RED);
                    currentIndex++;
                    System.out.println(currentIndex);

                    if (currentIndex == targetText.length()-1) {
                        long endTime = System.currentTimeMillis();
                        long timeTaken = endTime - startTime;

                        // Calculate words per minute (WPM)
                        int totalWords = targetText.split("\\s+").length;
                        double minutes = timeTaken / 60000.0; // Convert milliseconds to minutes
                        int wpm = (int) Math.round(totalWords / minutes);

                        JOptionPane.showMessageDialog(null, "Congratulations! You typed the sentence correctly.\nWPM: " + wpm);
                        resetGame();
                    }
                }
            }


            @Override
            public void keyPressed(KeyEvent e) {
                // Not needed for this example
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not needed for this example
            }
        });

        // Record the start time when the JFrame is created
        startTime = System.currentTimeMillis();
    }

    private void resetGame() {
        currentIndex = 0;
        for (JLabel label : labels) {
            label.setForeground(Color.BLACK);
        }
        panel.requestFocusInWindow();
        startTime = System.currentTimeMillis(); // Reset the start time

        // Reset the text in labels
        for (int i = 0; i < targetText.length(); i++) {
            labels[i].setText(String.valueOf(targetText.charAt(i)));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TypingGame typingGame = new TypingGame();
            typingGame.setVisible(true);
        }); 
    } 
    
    public void showTypingGame() {
        SwingUtilities.invokeLater(() -> {
            mainMenu.setVisible(false); // Hide the MainMenu window if needed
            this.setVisible(true);
        });
    }
}