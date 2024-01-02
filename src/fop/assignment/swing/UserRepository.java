/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static final String FILE_PATH = "C:\\Users\\user\\Desktop\\fop\\FOP ASSIGNMENT SWING\\userRepository.txt";
    private static UserRepository instance;
    private Map<String, User> users;

    private static User currentUser;
    private UserRepository() {
        // Private constructor to prevent instantiation
        users = new HashMap<>();
        loadDataFromFile();
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user){
        currentUser = user;
    }
    public User getUser(String username) {
        return users.get(username);
    }

    public void addUser(String username, User userData) {
        users.put(username, userData);
        saveDataToFile();
    }

    private void loadDataFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
        users = (HashMap<String, User>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        // Handle exceptions (e.g., file not found, class not found, etc.)
        // Initialize the users Map if the file is not present or empty
        users = new HashMap<>();
    }
}


    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users);
            System.out.println("Saved");
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found, etc.)
            e.printStackTrace();
        }
    }
    
    public Collection <User> getAllUser(){
        return users.values();
    
    }
}