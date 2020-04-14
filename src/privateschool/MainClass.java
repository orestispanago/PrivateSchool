package privateschool;

import java.sql.ResultSet;
import models.Trainer;
import models.Student;
import models.Course;
import models.Assignment;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.AssignmentData;
import models.AssignmentsCourses;
import models.CourseData;
import models.Database;
import models.StudentData;
import models.StudentsCourses;
import models.TrainerData;
import models.TrainersCourses;
import static privateschool.Check.isInputYes;

public class MainClass {

    public static List<Course> courses = new ArrayList();
    public static List<Student> students = new ArrayList();
    public static List<Trainer> trainers = new ArrayList();
    public static List<Assignment> assignments = new ArrayList();
    public static DateTimeFormatter formatterGreek = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatterSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Scanner scanner = new Scanner(System.in);

    public static Database db = new Database();
    public static String dashes = "--------------------------------------------------------------------------------";
    public static String equalsigns = "================================================================================";

    public static ResultSet getStudentsMoreThanOneCourse(Database db) {
        ResultSet assignmentsPerCourse = db.getResults(
                "SELECT \n"
                + "    students.id,\n"
                + "    students.first_name,\n"
                + "    students.last_name,\n"
                + "    COUNT(students_courses.id)\n"
                + "FROM\n"
                + "    students_courses,\n"
                + "    students\n"
                + "WHERE\n"
                + "    students.id = students_courses.students_id\n"
                + "GROUP BY students_id\n"
                + "HAVING COUNT(students_courses.students_id) > 1;");
        return assignmentsPerCourse;
    }
    static void dummyData() {
        System.out.print("Would you like to use dummy data? (y/n):");
        boolean useDummyData = isInputYes();
        if (useDummyData == true) {
            Database.deleteAll();;
        }
    }

    public static void main(String[] args) {
        dummyData();
        Menu.mainMenu();


    }
}
