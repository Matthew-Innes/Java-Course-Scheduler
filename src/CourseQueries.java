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


public class CourseQueries {
    
    private static Connection db_connection;
    
    private static PreparedStatement addCourse; 
    
    private static PreparedStatement getAllCourseCodes; 
    
    private static ResultSet settingResult; 
    
    
    public static void addCourse(CourseEntry course) {
        
        db_connection = DBConnection.getConnection();
        
        try {
            
            addCourse = db_connection.prepareStatement("INSERT INTO app.course (coursecode, description) VALUES (?, ?)");
            
            addCourse.setString(1, course.getCourseCode());
            
            addCourse.setString(2, course.getDescription());
            
            addCourse.executeUpdate(); 
            
        } catch(SQLException sqlException ) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
    
    public static ArrayList<String> getAllCourseCodes() {
        
        db_connection = DBConnection.getConnection();
        
        ArrayList<String> course_codes = new ArrayList<>();
        
        
        try {
            
            getAllCourseCodes = db_connection.prepareStatement("SELECT coursecode FROM app.course");
            
            settingResult = getAllCourseCodes.executeQuery();
            
            
            while(settingResult.next()) {
                
                course_codes.add(settingResult.getString(1));
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        
        return course_codes;
        
        
    }
    
    
}
