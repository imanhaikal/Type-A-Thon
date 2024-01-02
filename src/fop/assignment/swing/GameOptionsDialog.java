package fop.assignment.swing;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOptionsDialog {
    private JCheckBox punctuationCheckBox;
    private JRadioButton timer15Button;
    private JRadioButton timer30Button;
    private JRadioButton timer45Button;
    private JRadioButton timer60Button;
    private JFrame options;
    

    public GameOptionsDialog() {
        options = new JFrame();
        
        options.setSize(300, 250);
        options.setTitle("Game Options");
        options.setLocationRelativeTo(null);
        options.getContentPane().setBackground(Color.black);
        
        punctuationCheckBox = new JCheckBox("Include Punctuation");
        punctuationCheckBox.setBounds(50, 20, 200, 30);
        punctuationCheckBox.setFont(new Font("Monospaced", Font.BOLD, 15));
        punctuationCheckBox.setForeground(Color.yellow);
        punctuationCheckBox.setBackground(Color.black);
        punctuationCheckBox.setSelected(TimedGameMode.includePunctuation());
        
        punctuationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the includePunctuation flag when the checkbox state changes
                TimedGameMode.setIncludePunctuation(punctuationCheckBox.isSelected());
            }
        }); 

        timer15Button = new JRadioButton("15 seconds");
        timer15Button.setFont(new Font("Monospaced", Font.BOLD, 15));
        timer15Button.setForeground(Color.yellow);
        timer15Button.setBackground(Color.black);
        timer30Button = new JRadioButton("30 seconds");
        timer30Button.setFont(new Font("Monospaced", Font.BOLD, 15));
        timer30Button.setForeground(Color.yellow);
        timer30Button.setBackground(Color.black);
        timer45Button = new JRadioButton("45 seconds");
        timer45Button.setFont(new Font("Monospaced", Font.BOLD, 15));
        timer45Button.setForeground(Color.yellow);
        timer45Button.setBackground(Color.black);
        timer60Button = new JRadioButton("60 seconds");
        timer60Button.setFont(new Font("Monospaced", Font.BOLD, 15));
        timer60Button.setForeground(Color.yellow);
        timer60Button.setBackground(Color.black);

        ButtonGroup group = new ButtonGroup();

        timer15Button.setBounds(50, 50, 200, 30);
        timer30Button.setBounds(50, 80, 200, 30);
        timer45Button.setBounds(50, 110, 200, 30);
        timer60Button.setBounds(50, 140, 200, 30);

        group.add(timer15Button);
        group.add(timer30Button);
        group.add(timer45Button);
        group.add(timer60Button);

        JButton okButton = new JButton("OK");
        okButton.setBounds(120, 180, 60, 30);
        okButton.setFont(new Font("Monospaced", Font.BOLD, 15));
        okButton.setForeground(Color.yellow);
        okButton.setBackground(Color.black);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedDuration = getSelectedDuration();
                if (selectedDuration != -1) {
                    setTimerDuration(selectedDuration,punctuationCheckBox.isSelected());
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a timer duration");
                }
            }
        });

        options.add(punctuationCheckBox);
        options.add(timer15Button);
        options.add(timer30Button);
        options.add(timer45Button);
        options.add(timer60Button);
        options.add(okButton);

        options.setLayout(null);
        options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        options.setVisible(true);
    }

    private int getSelectedDuration() {
        if (timer15Button.isSelected()) {
            return 15;
        } else if (timer30Button.isSelected()) {
            return 30;
        } else if (timer45Button.isSelected()) {
            return 45;
        } else if (timer60Button.isSelected()) {
            return 60;
        }
        return -1; // No option selected
    }

    private void setTimerDuration(int seconds, boolean punctuation) {
        TimedGameMode timedGameMode = new TimedGameMode(seconds,punctuation);

        options.dispose(); // Close the Game Options dialog
    }
}
