/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SelectModeButton {
    protected static  int mode1 = 10;
    protected static  int mode2 = 25;
    protected static  int mode3 = 50;
    protected static  int mode4 = 100;
	
    public SelectModeButton(){
        JFrame buttonmode = new JFrame();
			
	buttonmode.setSize(570,670);
	buttonmode.setTitle("Game mode");
	buttonmode.setDefaultCloseOperation(buttonmode.DISPOSE_ON_CLOSE);
	buttonmode.setBackground(Color.black);
	JPanel panel = new JPanel(new GridLayout(4, 1));


	        JButton Quote = new JButton("10");
	        Quote.setOpaque(true);
			Quote.setFont(new Font("Monospaced", Font.BOLD, 15));
			Quote.setForeground(Color.yellow);
			Quote.setBackground(Color.black);
			Quote.setHorizontalAlignment(JButton.CENTER);
	        Quote.setBounds(85, 240, 100, 35);
	 
	    
	        Quote.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest(10);
	            	words.wordCount = 10;
                        
	            }
	        });
	        JButton Words = new JButton("25");
	        Words.setOpaque(true);
			Words.setFont(new Font("Monospaced", Font.BOLD, 15));
			Words.setForeground(Color.yellow);
			Words.setBackground(Color.black);
			Words.setHorizontalAlignment(JButton.CENTER);
	        Words.setBounds(85, 240, 100, 35);
	        Words.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest(25);
	                
	            }
	        });
	        JButton Time = new JButton("50");
	        Time.setOpaque(true);
	        Time.setFont(new Font("Monospaced", Font.BOLD, 15));
	        Time.setForeground(Color.yellow);
	        Time.setBackground(Color.black);
	        Time.setHorizontalAlignment(JButton.CENTER);
	        Time.setBounds(85, 240, 100, 35);
	 
	    
	        Time.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest(50);
	               
	            }
	        });
	        JButton Sudden_Death = new JButton("100");
	        Sudden_Death.setOpaque(true);
	        Sudden_Death.setFont(new Font("Monospaced", Font.BOLD, 15));
	        Sudden_Death.setForeground(Color.yellow);
	        Sudden_Death.setBackground(Color.black);
	        Sudden_Death.setHorizontalAlignment(JButton.CENTER);
	        Sudden_Death.setBounds(85, 240, 100, 35);
	 
	    
	        Sudden_Death.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest(100);
	               
	            }
	        });
	        Quote.setBorderPainted(false);
	        Words.setBorderPainted(false);
	        Time.setBorderPainted(false);
	        Sudden_Death.setBorderPainted(false);
	        
	        panel.add(Quote);
	        panel.add(Words);
	        panel.add(Time);
	        panel.add(Sudden_Death);
	   
	        buttonmode.setLocationRelativeTo(null);
	        buttonmode.add(panel);
			buttonmode.getContentPane().setBackground(Color.DARK_GRAY);
			buttonmode.setVisible(true);
    }
/*    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
	JFrame buttonmode = new JFrame();
			
	buttonmode.setSize(570,670);
	buttonmode.setTitle("Game mode");
	buttonmode.setDefaultCloseOperation(buttonmode.EXIT_ON_CLOSE);
	buttonmode.setBackground(Color.black);
	JPanel panel = new JPanel(new GridLayout(4, 1));


	        JButton Quote = new JButton("10");
	        Quote.setOpaque(true);
			Quote.setFont(new Font("Monospaced", Font.BOLD, 15));
			Quote.setForeground(Color.yellow);
			Quote.setBackground(Color.black);
			Quote.setHorizontalAlignment(JButton.CENTER);
	        Quote.setBounds(85, 240, 100, 35);
	 
	    
	        Quote.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest();
	            	words.wordCount = 10;
	            	
	            	words.main(args);
	            }
	        });
	        JButton Words = new JButton("25");
	        Words.setOpaque(true);
			Words.setFont(new Font("Monospaced", Font.BOLD, 15));
			Words.setForeground(Color.yellow);
			Words.setBackground(Color.black);
			Words.setHorizontalAlignment(JButton.CENTER);
	        Words.setBounds(85, 240, 100, 35);
	        Words.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest();
	            	words.wordCount = 25;
	            	
	            	words.main(args);
	                
	            }
	        });
	        JButton Time = new JButton("50");
	        Time.setOpaque(true);
	        Time.setFont(new Font("Monospaced", Font.BOLD, 15));
	        Time.setForeground(Color.yellow);
	        Time.setBackground(Color.black);
	        Time.setHorizontalAlignment(JButton.CENTER);
	        Time.setBounds(85, 240, 100, 35);
	 
	    
	        Time.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest();
	            	words.wordCount = 50;
	            	
	            	words.main(args);
	               
	            }
	        });
	        JButton Sudden_Death = new JButton("100");
	        Sudden_Death.setOpaque(true);
	        Sudden_Death.setFont(new Font("Monospaced", Font.BOLD, 15));
	        Sudden_Death.setForeground(Color.yellow);
	        Sudden_Death.setBackground(Color.black);
	        Sudden_Death.setHorizontalAlignment(JButton.CENTER);
	        Sudden_Death.setBounds(85, 240, 100, 35);
	 
	    
	        Sudden_Death.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e){
	            	OkaGameModeTest words = new OkaGameModeTest();
	            	words.wordCount = 100;
	            	
	            	words.main(args);
	               
	            }
	        });
	        Quote.setBorderPainted(false);
	        Words.setBorderPainted(false);
	        Time.setBorderPainted(false);
	        Sudden_Death.setBorderPainted(false);
	        
	        panel.add(Quote);
	        panel.add(Words);
	        panel.add(Time);
	        panel.add(Sudden_Death);
	   
	        buttonmode.setLocationRelativeTo(null);
	        buttonmode.add(panel);
			buttonmode.getContentPane().setBackground(Color.DARK_GRAY);
			buttonmode.setVisible(true);

		} */

	

}

