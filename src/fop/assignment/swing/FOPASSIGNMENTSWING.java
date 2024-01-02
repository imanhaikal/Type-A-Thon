/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fop.assignment.swing;

import java.io.File;
public class FOPASSIGNMENTSWING  {

    
     public static void main(String[] args) {
        // Specify the path to your text file
        String absolutePath = new File("C:\\Users\\user\\Desktop\\fop\\FOP ASSIGNMENT SWING\\userRepository.txt\"").getAbsolutePath();
        File file = new File(absolutePath);
        //String filePath = "\"C:\\SEM 1\\FOP\\userRepository.txt\"";

        // Create a File object
        //File file = new File(filePath);

        // Check if the file is read-only
        if (file.exists() && file.isFile()) {
            if (file.canWrite()) {
                System.out.println("File is not read-only. You can write to it.");
            } else {
                System.out.println("File is read-only. You cannot write to it.");
            }
        } else {
            System.out.println("File does not exist or is not a regular file.");
            if (file.exists()) {
             // Continue with read-only check
            } else {
              System.out.println("File does not exist.");
}
            

        
        }
    }
}