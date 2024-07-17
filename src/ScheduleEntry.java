/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author matthewinnes
 */

import java.sql.Timestamp;


public class ScheduleEntry {
    
    private String Semester; 
    
    private String CourseCode; 
    
    private String StudentID; 
    
    private String Status; 
    
    private  Timestamp timeStamp;
    
    
    public ScheduleEntry(String Semester, String CourseCode, String StudentID, String Status, Timestamp timeStamp) {
        
        this.Semester = Semester; 
        
        this.CourseCode = CourseCode; 
        
        this.StudentID = StudentID; 
        
        this.Status = Status; 
        
        this.timeStamp = timeStamp; 
        
    }
    
    
    public String getSemester() {
        
        return Semester; 
        
    }
    
    
    public String getCourseCode() {
        
        return CourseCode;
        
    }
    
    
    public String getStudentID() {
        
        return StudentID; 
        
    }
    
    
    public String getStatus() {
        
        return Status; 
        
    }
    
    
    public Timestamp get_timeStamp() {
        
        return timeStamp; 
        
    }
    
}
