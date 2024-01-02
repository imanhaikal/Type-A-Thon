package fop.assignment.swing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LeaderboardAllTimeAvg extends JFrame{
    private UserRepository userRepository;

    public LeaderboardAllTimeAvg() {
        userRepository = UserRepository.getInstance();

        setTitle("Leaderboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        List<User> topPlayers = getTopPlayers();
        LeaderboardTableModel model = new LeaderboardTableModel(topPlayers);

        JTable table = new JTable(new LeaderboardTableModel(topPlayers));
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private List<User> getTopPlayers() {
    List<User> allUsers = new ArrayList<>(userRepository.getAllUser());
    
    Collections.sort(allUsers, Comparator.comparingDouble(User::getAllTimeAverageWPM).reversed());

    return allUsers.subList(0, Math.min(allUsers.size(), 10));
    }

    private static class LeaderboardTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Username", "All-time Average WPM"};

        private final List<User> topPlayers;

        public LeaderboardTableModel(List<User> topPlayers) {
            this.topPlayers = topPlayers;
        }

        @Override
        public int getRowCount() {
            return topPlayers.size();
        }

        @Override
        public int getColumnCount() {
            return 2; // Two columns: Username and All-time Average WPM
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            User user = topPlayers.get(rowIndex);

            if (columnIndex == 0) {
                return user.getUsername();
            } else if (columnIndex == 1) {
                return user.getAllTimeAverageWPM();
            }

            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    public void showAllTimeLeaderboard() {
        this.setVisible(true);
    }
}

