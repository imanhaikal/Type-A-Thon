/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

public class UserProfiles extends JFrame {
    private List<User> userList;
    private UserRepository userRepository;
    public UserProfiles(){
        userRepository = UserRepository.getInstance();
        
        this.userList= List.copyOf(userRepository.getAllUser());
        
        setTitle("Type-a-thon");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        LeaderboardTableModel model = new LeaderboardTableModel();
        
        JTable table = new JTable(model);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
   
  /*  private void setColumnWidths(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        // Set different preferred widths for each column
        columnModel.getColumn(0).setPreferredWidth(100); // Username
        columnModel.getColumn(1).setPreferredWidth(150); // Last 10 WPM Average
        columnModel.getColumn(2).setPreferredWidth(150); // All Time Average WPM
        columnModel.getColumn(3).setPreferredWidth(120); // Sudden Death Score
        columnModel.getColumn(4).setPreferredWidth(300); // 10 most mispelled words
    } */
    
    private class LeaderboardTableModel extends AbstractTableModel{
        
        private final String[] columnNames = {"Username","Last 10 WPM Average", "All-time WPM Average","Sudden Death Score","Average Accuracy","10 most mispelled words"};
    
        //public LeaderboardTableModel(List<User> userList){
         //   this.userList = userList;
        //}
        @Override
        public int getRowCount() {
        return userList.size();
        }

        //@Override
        public int getColumnCount() {
        //To display five columns: username, last10wpmaverage, all time average, sudden death score, mispelled words
        return 6;
        }

        //@Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);

        // Column 0: Username
        if (columnIndex == 0) {
            return user.getUsername();
        }
        // Column 1: Last 10 WPM Average
        else if (columnIndex == 1) {
            return user.getLastTenWpmAverage();
        }
        // Column 2: All Time Average WPM
        else if (columnIndex == 2) {
            return user.getAllTimeAverageWPM();
        }
        // Column 3: Sudden Death Score
        else if (columnIndex == 3) {
            return user.getSuddenDeathScore();
        }
        else if (columnIndex == 4) {
            return user.getCalculatedAccuracy();
        }
        else if (columnIndex == 5) {
            return user.getWrongWords();
        }
        
        return null; // Default case
    }

    //@Override
    public String getColumnName(int column) {
        // Provide column names
        if (column == 0) {
            return "Username";
        } else if (column == 1) {
            return "Last 10 WPM Average";
        } else if (column == 2) {
            return "All Time Average WPM";
        } else if (column == 3) {
            return "Sudden Death Score";
        } else if (column == 4) {
            return "Average Accuracy";
        }else if (column == 5) {
            return "10 most mispelled words";
        }
        
        return ""; // Default case
        }
    }
    
    public void showUserProfile(){
        this.setVisible(true);
    }
   
}