/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class ButtonSet {
    JFrame buttonmode;
    public ButtonSet(){
        buttonmode = new JFrame();
		
buttonmode.setSize(570,670);
buttonmode.setTitle("Game mode");
buttonmode.setDefaultCloseOperation(buttonmode.EXIT_ON_CLOSE);
buttonmode.setBackground(Color.black);
JPanel panel = new JPanel(new GridLayout(6, 1));

        JButton   Classic = new JButton("Classic");
        Classic.setOpaque(true);
        Classic.setFont(new Font("Monospaced", Font.BOLD, 15));
        Classic.setForeground(Color.yellow);
        Classic.setBackground(Color.black);
        Classic.setHorizontalAlignment(JButton.CENTER);
        Classic.setBounds(85, 240, 100, 35);
 
    
        Classic.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               ClassicGameMode classic = new ClassicGameMode();
            }
        });
        
        
        JButton Quote = new JButton("Quote");
        Quote.setOpaque(true);
		Quote.setFont(new Font("Monospaced", Font.BOLD, 15));
		Quote.setForeground(Color.yellow);
		Quote.setBackground(Color.black);
		Quote.setHorizontalAlignment(JButton.CENTER);
        Quote.setBounds(85, 240, 100, 35);
 
    
        Quote.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               
            }
        });
        JButton Words = new JButton("Words");
        Words.setOpaque(true);
		Words.setFont(new Font("Monospaced", Font.BOLD, 15));
		Words.setForeground(Color.yellow);
		Words.setBackground(Color.black);
		Words.setHorizontalAlignment(JButton.CENTER);
        Words.setBounds(85, 240, 100, 35);
        Words.addActionListener(new ActionListener(){
          
          
            @Override
            public void actionPerformed(ActionEvent e){
            	SelectModeButton words = new SelectModeButton();
                
            }
        });
        JButton Time = new JButton("Time");
        Time.setOpaque(true);
        Time.setFont(new Font("Monospaced", Font.BOLD, 15));
        Time.setForeground(Color.yellow);
        Time.setBackground(Color.black);
        Time.setHorizontalAlignment(JButton.CENTER);
        Time.setBounds(85, 240, 100, 35);
 
    
        Time.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                GameOptionsDialog optionsDialog = new GameOptionsDialog();
                optionsDialog.setVisible(true);
            }
        });
        JButton Sudden_Death = new JButton("Sudden Death");
        Sudden_Death.setOpaque(true);
        Sudden_Death.setFont(new Font("Monospaced", Font.BOLD, 15));
        Sudden_Death.setForeground(Color.yellow);
        Sudden_Death.setBackground(Color.black);
        Sudden_Death.setHorizontalAlignment(JButton.CENTER);
        Sudden_Death.setBounds(85, 240, 100, 35);
 
    
        Sudden_Death.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SuddenDeathGameMode suddenDeath = new SuddenDeathGameMode(100);
            }
        });
        JButton   Correction_Facility = new JButton("Correction Facility");
        Correction_Facility.setOpaque(true);
        Correction_Facility.setFont(new Font("Monospaced", Font.BOLD, 15));
        Correction_Facility.setForeground(Color.yellow);
        Correction_Facility.setBackground(Color.black);
        Correction_Facility.setHorizontalAlignment(JButton.CENTER);
        Correction_Facility.setBounds(85, 240, 100, 35);
 
    
        Correction_Facility.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               CorrectionFacility correctionFacility = new CorrectionFacility();
            }
        });
        
        Classic.setBorderPainted(false);
        Quote.setBorderPainted(false);
        Words.setBorderPainted(false);
        Time.setBorderPainted(false);
        Sudden_Death.setBorderPainted(false);
        Correction_Facility.setBorderPainted(false);
        panel.add(Classic);
        panel.add(Quote);
        panel.add(Words);
        panel.add(Time);
        panel.add(Sudden_Death);
        panel.add(Correction_Facility);
        buttonmode.setLocationRelativeTo(null);
        buttonmode.add(panel);
        buttonmode.getContentPane().setBackground(Color.DARK_GRAY);
	

	
    }	
  /*  
    public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame buttonmode = new JFrame();
		
buttonmode.setSize(570,670);
buttonmode.setTitle("Game mode");
buttonmode.setDefaultCloseOperation(buttonmode.EXIT_ON_CLOSE);
buttonmode.setBackground(Color.black);
JPanel panel = new JPanel(new GridLayout(5, 1));


        JButton Quote = new JButton("Quote");
        Quote.setOpaque(true);
		Quote.setFont(new Font("Monospaced", Font.BOLD, 15));
		Quote.setForeground(Color.yellow);
		Quote.setBackground(Color.black);
		Quote.setHorizontalAlignment(JButton.CENTER);
        Quote.setBounds(85, 240, 100, 35);
 
    
        Quote.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               
            }
        });
        JButton Words = new JButton("Words");
        Words.setOpaque(true);
		Words.setFont(new Font("Monospaced", Font.BOLD, 15));
		Words.setForeground(Color.yellow);
		Words.setBackground(Color.black);
		Words.setHorizontalAlignment(JButton.CENTER);
        Words.setBounds(85, 240, 100, 35);
        Words.addActionListener(new ActionListener(){
          
          
            @Override
            public void actionPerformed(ActionEvent e){
            	SelectModeButton words = new SelectModeButton(args);
                
            }
        });
        JButton Time = new JButton("Time");
        Time.setOpaque(true);
        Time.setFont(new Font("Monospaced", Font.BOLD, 15));
        Time.setForeground(Color.yellow);
        Time.setBackground(Color.black);
        Time.setHorizontalAlignment(JButton.CENTER);
        Time.setBounds(85, 240, 100, 35);
 
    
        Time.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               
            }
        });
        JButton Sudden_Death = new JButton("Sudden Death");
        Sudden_Death.setOpaque(true);
        Sudden_Death.setFont(new Font("Monospaced", Font.BOLD, 15));
        Sudden_Death.setForeground(Color.yellow);
        Sudden_Death.setBackground(Color.black);
        Sudden_Death.setHorizontalAlignment(JButton.CENTER);
        Sudden_Death.setBounds(85, 240, 100, 35);
 
    
        Sudden_Death.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               
            }
        });
        JButton   Correction_Facility = new JButton("Correction Facility");
        Correction_Facility.setOpaque(true);
        Correction_Facility.setFont(new Font("Monospaced", Font.BOLD, 15));
        Correction_Facility.setForeground(Color.yellow);
        Correction_Facility.setBackground(Color.black);
        Correction_Facility.setHorizontalAlignment(JButton.CENTER);
        Correction_Facility.setBounds(85, 240, 100, 35);
 
    
        Correction_Facility.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               
            }
        });
        Quote.setBorderPainted(false);
        Words.setBorderPainted(false);
        Time.setBorderPainted(false);
        Sudden_Death.setBorderPainted(false);
        Correction_Facility.setBorderPainted(false);
        panel.add(Quote);
        panel.add(Words);
        panel.add(Time);
        panel.add(Sudden_Death);
        panel.add(Correction_Facility);
        buttonmode.setLocationRelativeTo(null);
        buttonmode.add(panel);
		buttonmode.getContentPane().setBackground(Color.DARK_GRAY);
		buttonmode.setVisible(true);

	} */

}

 