package fop.assignment.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ClassicGameMode {

    public static int mistake = 0;
    public static int typeRightWord = 0;
    static String textPool11;
    public static int wordCount;
    static boolean stopWatchClock = false;
    static long startTime;
    int currentIndex = 0;
    HashMap<String, Integer> wrongWords;
    boolean[] mistakes;
    String targetText;
    private int correctChar = 0;

    private Timer timer;
    private JLabel timerLabel;

    public ClassicGameMode() {
        this.wrongWords = UserRepository.getInstance().getCurrentUser().getWrongWords();
        this.wordCount = 110;
        textPool11 = wordPool(wordCount);
        targetText = ParagraphGenerator.generateRandomSentence(110);
        mistakes = new boolean[targetText.length()];
        startTime = System.currentTimeMillis();

        JFrame gamemode = new JFrame();
        gamemode.setSize(600, 670);
        gamemode.setTitle("Game mode");
        gamemode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamemode.setBackground(Color.black);
        gamemode.setLocationRelativeTo(null);

        JEditorPane screenText = new JEditorPane("text/html", " ");
        screenText.setEditable(false);
        screenText.setForeground(Color.green);
        screenText.setFont(new Font("Monospaced", Font.BOLD, 15));
        screenText.setBackground(Color.black);
        screenText.setOpaque(true);
        screenText.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        screenText.setBounds(0, 50, 570, 200);

        JLabel title = new JLabel("Start to type and press Complete to end");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(Color.yellow);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        title.setBounds(0, 0, 570, 50);

        timerLabel = new JLabel("Time remaining: 30 seconds");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setVerticalAlignment(JLabel.TOP);
        timerLabel.setVerticalTextPosition(JLabel.CENTER);
        timerLabel.setHorizontalTextPosition(JLabel.CENTER);
        timerLabel.setForeground(Color.yellow);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        timerLabel.setBounds(0, 250, 570, 50);

        screenText.setText(parseHtml(targetText, 0));

        timer = new Timer(1000, new ActionListener() {
            int secondsRemaining = 30;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsRemaining > 0) {
                    secondsRemaining--;
                    timerLabel.setText("Time remaining: " + secondsRemaining + " seconds");
                } else {
                    stopTimer();
                    long endTime = System.currentTimeMillis();
                    long timeTaken = endTime - startTime;
                    wpmCaculator(timeTaken, wordCount);
//                    JOptionPane.showMessageDialog(null, wrongWords.toString());
                    UserRepository.getInstance().saveDataToFile();
                }
            }
        });

        screenText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                char targetChar = targetText.charAt(currentIndex);

                if (typedChar == targetChar) {
                    currentIndex++;
                    typeRightWord++;
                    correctChar++;
                    
                    screenText.setText(parseHtml(targetText, currentIndex));

                    if (currentIndex == targetText.length()) {
                        stopTimer();
                        long endTime = System.currentTimeMillis();
                        long timeTaken = endTime - startTime;
                        wpmCaculator(timeTaken, wordCount);
//                        JOptionPane.showMessageDialog(null, wrongWords.toString());
                        UserRepository.getInstance().saveDataToFile();
                    }

                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (currentIndex > 0) {
                        currentIndex--;
                        if(mistakes[currentIndex]) mistake--;
                        mistakes[currentIndex] = false;
                        screenText.setText(parseHtml(targetText, currentIndex));
                    }
                } else {
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
                startTimer();  // Start the timer when any key is pressed
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

        gamemode.add(screenText);
        gamemode.add(title);
        gamemode.add(timerLabel);
        gamemode.setLayout(null);
        gamemode.getContentPane().setBackground(Color.DARK_GRAY);
        gamemode.setVisible(true);
        gamemode.setFocusable(true);
        screenText.grabFocus();
    }

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

    public void wpmCaculator(long timeTaken, double word) {
        
        double wpmInitial = (correctChar/5)*2;
        int wpm = (int) wpmInitial;

        double accuracy = (typeRightWord / (double) (typeRightWord + mistake)) * 100;

        String message = "Words Per Minute (WPM): " + wpm + "\n"
                + "Mistakes: " + mistake + "\n"
                + "Accuracy: " + accuracy + "%";

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
        
    }

    public static String wordPool(int length) {
        return ParagraphGenerator.generateRandomSentence(length);
    }

    private void startTimer() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    private void stopTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }
}
