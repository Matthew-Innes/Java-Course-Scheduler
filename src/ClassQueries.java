/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author matthewinnes
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClassQueries {
    
    private static Connection db_connection;
    
    private static PreparedStatement addClass; 
    
    private static PreparedStatement getAllCourseCodes; 
    
    private static PreparedStatement getClassSeats; 
    
    private static ResultSet settingResult; 
    
    private static PreparedStatement dropClass;
    
    
    public static void addClass(ClassEntry classEntry) {
        
        db_connection = DBConnection.getConnection();
        
        try {
            
            addClass = db_connection.prepareStatement("INSERT INTO app.class (semester, coursecode, seats) values (?, ?, ?)");
            
            addClass.setString(1, classEntry.getSemester());
            
            addClass.setString(2, classEntry.getCourseCode());
            
            addClass.setInt(3, classEntry.getSeats());
            
            addClass.executeUpdate();
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
    
    public static ArrayList<String> getAllCourseCodes(String semester) {
        
        db_connection = DBConnection.getConnection();
        
        ArrayList<String> course_codes = new ArrayList<>();
        
        
        try {
            
            getAllCourseCodes = db_connection.prepareStatement("SELECT coursecode FROM app.class WHERE semester = ? ORDER BY coursecode");
            
            getAllCourseCodes.setString(1, semester);
            
            settingResult = getAllCourseCodes.executeQuery();
            
            
            while(settingResult.next()) {
                
                course_codes.add(settingResult.getString(1));
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return course_codes;
        
    }
    
    
    public static int getClassSeats(String semester, String courseCode) {
        
        db_connection = DBConnection.getConnection();
        
        int class_seats = 0; 
        
        try {
            
            
            getClassSeats = db_connection.prepareStatement("SELECT seats FROM app.class WHERE semester = ? AND coursecode = ?");
            
            getClassSeats.setString(1, semester); 
            
            getClassSeats.setString(2, courseCode);
            
            settingResult = getClassSeats.executeQuery();
            
            
            if(settingResult.next()) {
                
                class_seats = settingResult.getInt(1); 
                
            }
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return class_seats;
        
    }
    
    
    public static void dropClass(String semester, String courseCode) {
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            dropClass = db_connection.prepareStatement("DELETE FROM app.class WHERE semester = ? AND coursecode = ?");
            
            dropClass.setString(1, semester);
            
            dropClass.setString(2, courseCode);
            
            int dropping_rows = dropClass.executeUpdate(); 
            
            
            if (dropping_rows > 0) {
                
                System.out.println("Class dropped successfully.");
                
            } else {
                
                System.out.println("Class not found or couldn't be dropped.");
                
            }
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
}
