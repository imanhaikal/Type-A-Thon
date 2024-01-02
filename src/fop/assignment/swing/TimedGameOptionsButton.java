/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimedGameOptionsButton extends JButton {
    public TimedGameOptionsButton() {
        setText("Game Options");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameOptionsDialog();
            }
        });
    }

    private void showGameOptionsDialog() {
        GameOptionsDialog optionsDialog = new GameOptionsDialog();
        optionsDialog.setVisible(true);
    }
}



