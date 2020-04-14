package privateschool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Course;
import models.Student;
import static privateschool.MainClass.courses;
import static privateschool.MainClass.students;

public class Print {

    private static String dashes = "--------------------------------------------------------------------------------";
    private static String equalsigns = "================================================================================";

    public static void printAllStudents(ResultSet rs) {
        try {

            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-29s|%-15s|%-5s|\n";
            String header = String.format(stringFormat, "ID", "First name", "Last Name", "Date of Birth", "fees");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printAllCourses(ResultSet rs) {
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-10s|%-14s|%-12s|%-12s|\n";
            String header = String.format(stringFormat, "ID", "Title", "Stream", "Type", "Start date", "End date");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printAllTrainers(ResultSet rs) {
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-29s|%-21s|\n";
            String header = String.format(stringFormat, "ID", "First name", "Last name", "Subject");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printAllAssignments(ResultSet rs) {
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-20s|%-10s|%-10s|\n";
            String header = String.format(stringFormat, "ID", "Title", "Description", "Submission datetime", "Tot mark");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printStudentsPerCourse(ResultSet rs) {
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-14s|%-22s|%-14s|%-12s|%-6s|\n";
            String header = String.format(stringFormat, "ID", "First name", "Last name", "Title", "Type", "Stream");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printTrainersPerCourse(ResultSet rs) {
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-14s|%-22s|%-14s|%-12s|%-6s|\n";
            String header = String.format(stringFormat, "ID", "First name", "Last name", "Title", "Type", "Stream");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printAssignmentsPerCourse(ResultSet rs) {
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-18s|%-18s|%-14s|%-12s|%-6s|\n";
            String header = String.format(stringFormat, "ID", "Title", "Description", "Course title", "Type", "Stream");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printStudentsMoreThanOneCourse(ResultSet rs) {
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-29s|%-21s|\n";
            String header = String.format(stringFormat, "ID", "First name", "Last name", "Number of Courses");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
       public static void printList(List listName) {
        for (int i = 0; i < listName.size(); i++) {
            System.out.print(i + 1 + "." + listName.get(i).toString());
        }
    }

    static void printTrainersPerCourse() {
        System.out.println("Printing trainers per course...");
        for (Course co : courses) {
            System.out.print(co);
            printList(co.getTrainers());
        }
    }

    static void printStudentsPerCourse() {
        System.out.println("Printing students per course...");
        for (Course co : courses) {
            System.out.print(co);
            printList(co.getStudents());
        }
    }

    static void printAssignmentsPerCourse() {

        System.out.println("Printing assignments per course...");
        for (Course co : courses) {
            System.out.print(co);
            printList(co.getAssignments());
        }
    }

    static void printAssignmentsPerStudent() {
        System.out.println("Printing assignments per student...");
        for (Student st : students) {
            System.out.print(st);
            printList(st.getAssignments());
        }
    }

    static void printStudentsMoreThanOneCourse() {
        if (courses.size() < 2) {
            System.out.println("Only one course found, please enter more from the main menu.");
        } else {
            System.out.println("Printing students enrolled to more than one course...");
            for (Student st : students) {
                if (st.getCoursesNumber() > 1) {
                    System.out.print(st);
                    printList(st.getCourses());
                }
            }
        }
    }


}
