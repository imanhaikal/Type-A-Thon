/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop.assignment.swing;
import javax.swing.*;
import java.awt.*;

public class Randdom {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("HTML Styled Text Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // HTML content
            String htmlContent = "<html><body><h1 style='color: blue;'>Hello, <i style='color: red;'>World!</i></h1></body></html>";

            // Create a JEditorPane with HTML content type
            JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
            editorPane.setEditable(false);

            // Add the editor pane to the frame
            frame.add(new JScrollPane(editorPane));

            frame.setVisible(true);
        });
    }
}

