/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    
    private User currentUser;
    
    public MainMenu(User user){
        this.currentUser = user;
       
        // setting gridbag layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridbag = new GridBagConstraints();
        gridbag.insets = new Insets(15, 15, 15, 15);
        setTitle("Type-a-thon");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(Color.BLACK);
        
        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getUsername()+"!",JLabel.CENTER);
        welcomeLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.GREEN);
        gridbag.gridx = 0;
        gridbag.gridy = 0;
        gridbag.gridwidth = 2;
        add(welcomeLabel, gridbag);
        
        JLabel lastTenWPMLabel = new JLabel("Last 10 average WPM: " + currentUser.getLastTenWpmAverage());
        JLabel allTimeAverageWPMLabel = new JLabel("All-time Average WPM: " + currentUser.getAllTimeAverageWPM());
        JLabel suddenDeathScoreLabel = new JLabel("Sudden Death Score: " + currentUser.getSuddenDeathScore());
        
        lastTenWPMLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        lastTenWPMLabel.setForeground(Color.GREEN);
        
        allTimeAverageWPMLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        allTimeAverageWPMLabel.setForeground(Color.GREEN);
        
        suddenDeathScoreLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        suddenDeathScoreLabel.setForeground(Color.GREEN);
        
        gridbag.gridx = 0;
        gridbag.gridy = 1;
        gridbag.gridwidth = 2;
        add(lastTenWPMLabel, gridbag);

        gridbag.gridx = 0;
        gridbag.gridy = 2;
        gridbag.gridwidth = 2;
        add(allTimeAverageWPMLabel, gridbag);

        gridbag.gridx = 0;
        gridbag.gridy = 3;
        gridbag.gridwidth = 2;
        add(suddenDeathScoreLabel, gridbag);

        JButton playButton = new JButton("Play");
        playButton.setForeground(Color.GREEN);
        playButton.setBackground(Color.BLACK);
        
        gridbag.gridx = 0;
        gridbag.gridy = 4;
        gridbag.gridwidth = 2;
        add(playButton, gridbag);
    
         playButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showTypingGame();
            }
        });
    
    
        JButton userProfileButton = new JButton("User profiles");
        userProfileButton.setForeground(Color.GREEN);
        userProfileButton.setBackground(Color.BLACK);

        gridbag.gridx = 0;
        gridbag.gridy = 5;
        gridbag.gridwidth = 2;
        add(userProfileButton, gridbag);
        
        userProfileButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showUserProfile();
            }
        });
    
        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setForeground(Color.GREEN);
        leaderboardButton.setBackground(Color.BLACK);

        gridbag.gridx = 0;
        gridbag.gridy = 7;
        gridbag.gridwidth = 1;
        add(leaderboardButton, gridbag);
        
        leaderboardButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showLeaderboard();
            }
        });
        
        JButton AllTimeLeaderboardButton = new JButton("All-time average Leaderboard");
        AllTimeLeaderboardButton.setForeground(Color.GREEN);
        AllTimeLeaderboardButton.setBackground(Color.BLACK);

        gridbag.gridx = 1;
        gridbag.gridy = 7;
        gridbag.gridwidth = 1;
        add(AllTimeLeaderboardButton, gridbag);
        
        AllTimeLeaderboardButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showAllTimeLeaderboard();
            }
        });
        
        JButton SuddenDeathLeaderboardButton = new JButton("Sudden Death Leaderboard");
        SuddenDeathLeaderboardButton.setForeground(Color.GREEN);
        SuddenDeathLeaderboardButton.setBackground(Color.BLACK);

        gridbag.gridx = 2;
        gridbag.gridy = 7;
        gridbag.gridwidth = 1;
        add(SuddenDeathLeaderboardButton, gridbag);
        
        SuddenDeathLeaderboardButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showSuddenDeathLeaderboard();
            }
        });
    }   
    
    private void showSuddenDeathLeaderboard(){
        LeaderboardSuddenDeath leaderboard = new LeaderboardSuddenDeath();
        leaderboard.showSuddenDeathLeaderboard();
    } 
    
    private void showLeaderboard(){
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.showLeaderboard();
    }
    
    private void showAllTimeLeaderboard(){
        LeaderboardAllTimeAvg leaderboard = new LeaderboardAllTimeAvg();
        leaderboard.showAllTimeLeaderboard();
    }
        
    
    private void showUserProfile(){
        UserProfiles userProfile = new UserProfiles();
        userProfile.showUserProfile();
    }
    
    private void showTypingGame(){
    	ButtonSet buttonSet = new ButtonSet();
        buttonSet.buttonmode.setVisible(true);
        dispose();
    }
}
