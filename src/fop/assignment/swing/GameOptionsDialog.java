package fop.assignment.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOptionsDialog extends JFrame {
    private JCheckBox punctuationCheckBox;
    private JRadioButton timer15Button;
    private JRadioButton timer30Button;
    private JRadioButton timer45Button;
    private JRadioButton timer60Button;

    public GameOptionsDialog() {
        setSize(300, 250);
        setTitle("Game Options");
        setLocationRelativeTo(null);

        punctuationCheckBox = new JCheckBox("Include Punctuation");
        punctuationCheckBox.setBounds(50, 20, 200, 30);
        punctuationCheckBox.setSelected(TimedGameMode.includePunctuation());
        
        punctuationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the includePunctuation flag when the checkbox state changes
                TimedGameMode.setIncludePunctuation(punctuationCheckBox.isSelected());
            }
        }); 

        timer15Button = new JRadioButton("15 seconds");
        timer30Button = new JRadioButton("30 seconds");
        timer45Button = new JRadioButton("45 seconds");
        timer60Button = new JRadioButton("60 seconds");

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

        add(punctuationCheckBox);
        add(timer15Button);
        add(timer30Button);
        add(timer45Button);
        add(timer60Button);
        add(okButton);

        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        dispose(); // Close the Game Options dialog
    }
}
