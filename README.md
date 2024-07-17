Course Scheduler
Overview:
This project is a Course Scheduler that manages and organizes class scheduling information. The primary entities include courses, students, class details, and schedules. The scheduler interacts with a database where each table row is represented by a corresponding entry class in our codebase. These classes encapsulate the columns of the database tables.

Classes and Properties
Semester
Semester: This is represented by the String class since it only has one property, which is the semester name.
CourseEntry
CourseCode: String - The unique code identifying the course.
Description: String - A brief description of the course.
Constructor: Initializes CourseCode and Description.

Getters: Methods to access the properties of the class.

ClassEntry
Semester: String - The semester in which the class is offered.
CourseCode: String - The unique code of the course.
Seats: Integer - The number of seats available in the class.
Constructor: Initializes Semester, CourseCode, and Seats.

Getters: Methods to access the properties of the class.

ClassDescription
CourseCode: String - The unique code of the course.
Description: String - A brief description of the course.
Seats: Integer - The number of seats available in the class.
Constructor: Initializes CourseCode, Description, and Seats.

Getters: Methods to access the properties of the class.

StudentEntry
StudentID: String - The unique identifier for the student.
FirstName: String - The first name of the student.
LastName: String - The last name of the student.
Constructor: Initializes StudentID, FirstName, and LastName.

Getters: Methods to access the properties of the class.

ScheduleEntry
Semester: String - The semester for the scheduled course.
CourseCode: String - The unique code of the course.
StudentID: String - The unique identifier of the student.
Status: String - The status of the schedule entry, either "S" (Scheduled) or "W" (Waitlisted).
Timestamp: Timestamp - The timestamp of when the schedule entry was created or updated.
Constructor: Initializes Semester, CourseCode, StudentID, Status, and Timestamp.

Getters: Methods to access the properties of the class.

