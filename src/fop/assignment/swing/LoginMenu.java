/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LoginMenu extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserRepository userRepository;

    public LoginMenu() {
        userRepository = UserRepository.getInstance();
        setTitle("Type-a-thon");
        setSize(600, 400); // Size of tab
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Use gridbag layout to arrange
        setLocationRelativeTo(null);
        
        //Make the background black 
        getContentPane().setBackground(Color.BLACK);
        
        
        GridBagConstraints gridbag = new GridBagConstraints();
        gridbag.insets = new Insets(15,15,15,15);
        
        JLabel titleLabel = new JLabel("Welcome to type-a-thon!", JLabel.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        titleLabel.setForeground(Color.GREEN); 
        gridbag.gridx = 0;
        gridbag.gridy = 0;
        gridbag.gridwidth = 2;
        add(titleLabel,gridbag);

        JLabel subTitleLabel = new JLabel("by JaveJedi", JLabel.CENTER);
        subTitleLabel.setFont(new Font("Monospaced", Font.ITALIC, 14));
        subTitleLabel.setForeground(Color.GREEN); 
        gridbag.gridx = 0;
        gridbag.gridy = 1;
        gridbag.gridwidth = 2;
        add(subTitleLabel,gridbag);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.GREEN); 
        gridbag.gridx = 0;
        gridbag.gridy = 2;
        gridbag.gridwidth = 1;
        add(usernameLabel,gridbag);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.GREEN); 
        gridbag.gridx = 0;
        gridbag.gridy = 3;
        gridbag.gridwidth = 1;
        add(passwordLabel,gridbag);
        
        /*JLabel haventRegisterLabel = new JLabel("Haven't register?",JLabel.CENTER);
        haventRegisterLabel.setFont(new Font("Monospaced", Font.ITALIC, 16));
        haventRegisterLabel.setForeground(Color.GREEN); 
        gridbag.gridx = 0;
        gridbag.gridy = 5;
        gridbag.gridwidth = 2;
        add(haventRegisterLabel,gridbag);*/
        
        usernameField = new JTextField();
        usernameField.setForeground(Color.BLACK); 
        usernameField.setBackground(Color.WHITE); 
        gridbag.gridx = 1;
        gridbag.gridy = 2;
        gridbag.gridwidth = 1;
        gridbag.fill = GridBagConstraints.HORIZONTAL; 
        //usernameField.setPreferredSize(new Dimension(150,25));
        add(usernameField,gridbag);
        
        passwordField = new JPasswordField();
        passwordField.setForeground(Color.BLACK); 
        passwordField.setBackground(Color.WHITE);
        gridbag.gridx = 1;
        gridbag.gridy = 3;
        gridbag.gridwidth = 1;
        gridbag.fill = GridBagConstraints.HORIZONTAL;
        passwordField.setPreferredSize(new Dimension(150,25)); 
        add(passwordField,gridbag);
        
        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.GREEN); 
        loginButton.setBackground(Color.BLACK);
        gridbag.gridx = 1;
        gridbag.gridy = 4;
        //gridbag.gridwidth = 1; 
        add(loginButton,gridbag);
        
        getRootPane().setDefaultButton(loginButton);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setForeground(Color.GREEN); 
        registerButton.setBackground(Color.BLACK);
        gridbag.gridx = 0;
        gridbag.gridy = 4;
        //gridbag.gridwidth = 1; 
        add(registerButton,gridbag);
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        
        
        
    }

    private void login() {
        String username = usernameField.getText();
        // get password returns char
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        
        
        User currentUser =  userRepository.getUser(username);
        if(!currentUser.getPassword().equals(password)){
            currentUser = null ; 
            //JOptionPane.showMessageDialog(this, "Wrong password or username!");
        }
        if (currentUser != null) {
       
            MainMenu mainMenu = new MainMenu(currentUser);
            mainMenu.setVisible(true);
            userRepository.setCurrentUser(currentUser); 
            JOptionPane.showMessageDialog(this, "Login successful!");
    //MainMenu mainMenu = new MainMenu(username);
    //mainMenu.setVisible(true);
             dispose();
    //setVisible(false);
    
    } else {
        JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
   


    // check if username and password match
 /*   private boolean checkCredentials(String username,String password){
        String filePath = "C:\\text file\\userRepository.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line=reader.readLine()) != null){
                String [] credentials = line.split(",");
                if(credentials.length==2 && credentials[0].equals(username) && credentials[1].equals(password)){
                   return true; //if match return true 
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    } */
    
    private void register(){
        String username = usernameField.getText();
        char [] passwordChars= passwordField.getPassword();
        String password = new String(passwordChars);
        
        //Check if the username is registered
        if (usernameUsed(username)){
            JOptionPane.showMessageDialog(this, "Error: Username has been taken already");
            return;
        }
        // write the info into the txt file
        User newUser = new User(username, password, new ArrayList<>(), 0, 0,0.0);
        userRepository.addUser(username, newUser);

    JOptionPane.showMessageDialog(this, "Registration Successful!");
    }
    
  

       /* String filePath = "C:\\text file\\userRepository.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))){
            writer.write(username+","+password);
            writer.newLine();
            writer.flush();
            JOptionPane.showMessageDialog(this,"Registration Successful!");
        }catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error : Couldnt Register");
        }   
    }*/
       
    private boolean usernameUsed(String username){
        User user = userRepository.getUser(username);
        return user != null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginMenu().setVisible(true);
            }
        });
    }
}
