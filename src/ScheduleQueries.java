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
import java.sql.Timestamp;


public class ScheduleQueries {
    
    private static Connection db_connection; 
    
    private static PreparedStatement addScheduleEntry; 
    
    private static PreparedStatement getScheduleByStudent; 
    
    private static PreparedStatement getScheduledStudentCount; 
    
    private static ResultSet settingResult; 
    
    
    public static void addScheduleEntry(ScheduleEntry entry) {
        
        db_connection = DBConnection.getConnection(); 
        
        
        try {
            
            addScheduleEntry = db_connection.prepareStatement("INSERT INTO app.schedule (semester, coursecode, studentid, status, timestamp) VALUES (?, ?, ?, ?, ?)");
            
            addScheduleEntry.setString(1, entry.getSemester());
            
            addScheduleEntry.setString(2, entry.getCourseCode());
            
            addScheduleEntry.setString(3, entry.getStudentID());
            
            addScheduleEntry.setString(4, entry.getStatus());
            
            addScheduleEntry.setTimestamp(5, entry.get_timeStamp());
            
            addScheduleEntry.executeUpdate();
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID) {
        
        db_connection = DBConnection.getConnection();
        
        ArrayList<ScheduleEntry> entries = new ArrayList<>();
        
        
        try {
            
            getScheduleByStudent = db_connection.prepareStatement("SELECT semester, coursecode, studentid, status, timestamp FROM app.schedule WHERE semester = ? and studentid = ?");
            
            getScheduleByStudent.setString(1, semester);
            
            getScheduleByStudent.setString(2, studentID);
            
            settingResult = getScheduleByStudent.executeQuery();
            
            
            while(settingResult.next()) {
                
                String courseCode = settingResult.getString("coursecode");
                
                String status = settingResult.getString("status"); 
                
                Timestamp timeStamp = settingResult.getTimestamp("timestamp"); 
                
                ScheduleEntry entry = new ScheduleEntry(semester, courseCode, studentID, status, timeStamp);
                
                entries.add(entry); 
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return entries;
        
    }
    
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode) {
        
        db_connection = DBConnection.getConnection();
        
        int scheduled_count = 0;
        
        
        try {
            
            getScheduledStudentCount = db_connection.prepareStatement("select count(*) from app.schedule where semester = ? and coursecode = ?");
            
            getScheduledStudentCount.setString(1, currentSemester);
            
            getScheduledStudentCount.setString(2, courseCode);
            
            settingResult = getScheduledStudentCount.executeQuery();
            
            
            if (settingResult.next()) {
                
                scheduled_count = settingResult.getInt(1); 
                
            }
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return scheduled_count;
        
    }
    
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode) {
        
        db_connection = DBConnection.getConnection();
        
        PreparedStatement preparedStatement = null; 
        
        ResultSet result = null; 
        
        ArrayList<ScheduleEntry> waitlistedStudents = new ArrayList<>();
        
        StudentEntry studentEntry = null; 
        
        ScheduleEntry scheduleEntry = null; 
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("SELECT * FROM app.schedule WHERE semester = ? AND courseCode = ? AND status = 'W'");
            
            preparedStatement.setString(1, semester);
            
            preparedStatement.setString(2, courseCode);
            
            result = preparedStatement.executeQuery();
            
            
            while(result.next()) {
                
                scheduleEntry = new ScheduleEntry(
                
                        result.getString("semester"),
                        
                        result.getString("coursecode"),
                        
                        result.getString("studentID"),
                        
                        result.getString("status"),
                           
                        result.getTimestamp("timestamp")
                
                );
                
                waitlistedStudents.add(scheduleEntry);
                
            }
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
        return waitlistedStudents; 
        
    }
    
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode) {
        
        PreparedStatement preparedStatement = null; 
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("DELETE FROM app.schedule WHERE semester = ? AND studentID = ? AND courseCode = ?");
            
            preparedStatement.setString(1, semester);
            
            preparedStatement.setString(2, studentID);
            
            preparedStatement.setString(3, courseCode);
            
            int dropped_rows = preparedStatement.executeUpdate();
            
            
            if(dropped_rows > 0) {
                
                System.out.println("Course dropped from student's schedule successfully.");
                
            } else {
                
                System.out.println("Course not found in the student's schedule or couldn't be dropped.");
                
            }
            
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
    
    public static void dropScheduleByCourse(String semester, String courseCode) {
        
        PreparedStatement preparedStatement = null; 
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("DELETE FROM app.schedule WHERE semester = ? AND courseCode = ?");
            
            preparedStatement.setString(1, semester);
            
            preparedStatement.setString(2, courseCode);
            
            int dropped_rows = preparedStatement.executeUpdate();
            
            
            if(dropped_rows > 0) {
                
                System.out.println("Course dropped from all students' schedules successfully.");
                
            } else {
                
                System.out.println("Course not found in any student's schedule or couldn't be dropped.");
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
    
    public static void updateScheduleEntry(ScheduleEntry entry) {
        
        PreparedStatement preparedStatement = null; 
        
        db_connection = DBConnection.getConnection();
        
        
        try {
            
            preparedStatement = db_connection.prepareStatement("UPDATE app.schedule SET status = 'S' WHERE semester = ? and coursecode = ? and studentID = ?");
            
            preparedStatement.setString(1, entry.getSemester());
            
            preparedStatement.setString(2, entry.getCourseCode());
            
            preparedStatement.setString(3, entry.getStudentID());
            
            int dropped_rows = preparedStatement.executeUpdate();
            
            
            if(dropped_rows > 0) {
                
                System.out.println("Schedule entry updated successfully.");
                
            } else {
                
                System.out.println("Schedule entry not found or couldn't be updated.");
                
            }
            
        } catch(SQLException sqlException) {
            
            sqlException.printStackTrace();
            
        }
        
    }
    
}
