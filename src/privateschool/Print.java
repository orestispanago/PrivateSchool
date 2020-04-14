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
