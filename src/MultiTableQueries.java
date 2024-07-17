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

public class MultiTableQueries {
    
    private static Connection db_connection; 
    
    private static PreparedStatement getAllClassDescriptions;
    
    private static ResultSet settingResult; 
    
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester) {
        
        
        db_connection = DBConnection.getConnection();
        
        ArrayList<ClassDescription> descriptions = new ArrayList<>();
        
        
        try {
            
            getAllClassDescriptions = db_connection.prepareStatement("SELECT c.coursecode, c.description, ce.seats FROM app.course c JOIN app.class ce ON c.coursecode = ce.coursecode WHERE ce.semester = ?");
            
            getAllClassDescriptions.setString(1, semester);
            
            settingResult = getAllClassDescriptions.executeQuery();
            
            
            while(settingResult.next()) {
                
                String courseCode = settingResult.getString("coursecode"); 
                
                String description = settingResult.getString("description");
                
                int class_seats = settingResult.getInt("seats");
                
                ClassDescription classDescription = new ClassDescription(courseCode, description, class_seats);
                
                descriptions.add(classDescription); 
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return descriptions; 
        
    }
    
    
    public static ArrayList<StudentEntry> getScheduledStudentByClass(String semester, String courseCode) {
        
        PreparedStatement preparedStatement = null; 
        
        ResultSet result = null; 
        
        ArrayList<StudentEntry> scheduledStudents = new ArrayList<>();
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("SELECT app.schedule.studentID,firstname,lastname FROM app.schedule,app.student WHERE semester = ? AND courseCode = ? AND status = 'S' AND app.schedule.studentID=app.student.studentID"); 
            
            preparedStatement.setString(1, semester);
            
            preparedStatement.setString(2, courseCode);
            
            result = preparedStatement.executeQuery();
            
            
            while(result.next()) {
                
                StudentEntry studentEntry = new StudentEntry(
                        
                        result.getString("studentID"),
                        
                        result.getString("firstname"),
                        
                        result.getString("lastname")
                
                );
                
                scheduledStudents.add(studentEntry);
                
            } 
        
    } catch(SQLException sqlException) {
        
        sqlException.printStackTrace();
        
    }
    
    return scheduledStudents; 
        
    }
    
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode) {
        
        PreparedStatement preparedStatement = null;
        
        ResultSet result = null; 
        
        ArrayList<StudentEntry> waitlistedStudents = new ArrayList<>();
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("SELECT app.schedule.studentID,firstname,lastname FROM app.schedule,app.student WHERE semester = ? AND courseCode = ? AND status = 'W' AND app.schedule.studentID=app.student.studentID");
            
            preparedStatement.setString(1, semester);
            
            preparedStatement.setString(2, courseCode);
            
            result = preparedStatement.executeQuery();
            
            
            while(result.next()) {
                
                StudentEntry studentEntry = new StudentEntry(
                        
                        result.getString("studentID"),
                        
                        result.getString("firstname"),
                        
                        result.getString("lastname")
                
                
                );
                
                waitlistedStudents.add(studentEntry);
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return waitlistedStudents;
        
        
    }
}
    
