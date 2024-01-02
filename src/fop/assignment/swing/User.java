/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class User implements Serializable {
    private String username;
    private String password;
    private List<Integer> WPM;
    private HashMap<String,Integer> wrongWords;
    private double suddenDeathScore;
    private List<Double> accuracy;
    
    
     public User(String username, String password, List<Integer> WPM, int allTimeAverageWPM, int suddenDeathScore, double lastTenWpmAverage) {
        this.wrongWords = new HashMap<>();
        this.username = username;
        this.password = password;
        this.WPM = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
        this.suddenDeathScore = 0.0;
        this.accuracy = new ArrayList<>();
    }

    // Getters

    public HashMap<String, Integer> getWrongWords() {
        return wrongWords;
    }
    
     
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Integer> getWPM() {
        return WPM;
    }
    
    public List<Double> getAccuracy() {
        return accuracy;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
    public void setSuddenDeathScore(double suddenDeathScore) {
        this.suddenDeathScore = suddenDeathScore;
    }
 
    public double getSuddenDeathScore() {
        return suddenDeathScore;
    }
    
   

     public double calculateLastTenWpmAverage(List<Integer> lastTenWPM) {
        if (lastTenWPM.isEmpty()) {
            return 0.0;
        }

        int sum = lastTenWPM.stream().mapToInt(Integer::intValue).sum();
        return (double) sum / lastTenWPM.size();
    }
    
    public double getLastTenWpmAverage(){
        double sum = 0.0;
        for(int i=0; i<10;i++){
            sum+= WPM.get(WPM.size()-i-1);
        }
        return sum/10;
    } 
    public double getAllTimeAverageWPM(){
        double sum = 0.0;
        for(int i=9; i<WPM.size();i++){
            sum+= WPM.get(i);
        }
        return sum/(WPM.size()-10);
    } 

    public double getCalculatedAccuracy() {
        double averageAccuracy = 0.0;
        for(int i=0;i<accuracy.size();i++){
            averageAccuracy+= accuracy.get(i);       
        }
        return averageAccuracy/accuracy.size();
    }

 
}


