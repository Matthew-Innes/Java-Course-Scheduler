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


public class StudentQueries {
    
    private static Connection db_connection; 
    
    private static PreparedStatement addStudent; 
    
    private static PreparedStatement getAllStudents; 
    
    private static ResultSet settingResult; 
    
    
    public static void addStudent(StudentEntry student) {
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            addStudent = db_connection.prepareStatement("INSERT INTO app.student (studentid, firstname, lastname) VALUES (?, ?, ?)");
            
            addStudent.setString(1, student.getStudentID());
            
            addStudent.setString(2, student.getFirstName());
            
            addStudent.setString(3, student.getLastName());
            
            addStudent.executeUpdate();
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
    
    public static ArrayList<StudentEntry> getAllStudents() {
        
        db_connection = DBConnection.getConnection();
        
        ArrayList<StudentEntry> students = new ArrayList<>();
        
        
        try {
            
            getAllStudents = db_connection.prepareStatement("SELECT studentid, firstname, lastname FROM app.student ORDER BY studentid");
            
            settingResult = getAllStudents.executeQuery();
            
            
            while(settingResult.next()) {
                
                String studentID = settingResult.getString(1); 
                
                String firstName = settingResult.getString(2); 
                
                String lastName = settingResult.getString(3); 
                
                StudentEntry student = new StudentEntry(studentID, firstName, lastName); 
                
                students.add(student); 
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return students;
        
    }
    
    
    public static StudentEntry getStudent(String studentID) {
        
        PreparedStatement preparedStatement = null; 
        
        StudentEntry studentEntry = null; 
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("SELECT * FROM app.student WHERE studentID = ?");
            
            preparedStatement.setString(1, studentID);
            
            ResultSet result = preparedStatement.executeQuery();
            
            
            if(result.next()) {
                
                studentEntry = new StudentEntry(
                        
                        result.getString("StudentID"),
                        
                        result.getString("firstName"),
                        
                        result.getString("lastName")
                
                );
                
            } else {
                
                System.out.println("Student not found with ID: " + studentID);
                
            }
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return studentEntry; 
        
    }
    
    
    public static void dropStudent(String studentID) {
        
        PreparedStatement preparedStatement = null;
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("DELETE FROM app.student WHERE studentID = ?");
            
            preparedStatement.setString(1, studentID);
            
            int dropped_rows = preparedStatement.executeUpdate();
            
            
            if(dropped_rows > 0) {
                
                System.out.println("Student dropped successfully.");
                
            } else {
                
                System.out.println("Student not found or couldn't be dropped.");
                
            }
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
}
