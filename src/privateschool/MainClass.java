package privateschool;

import java.sql.ResultSet;
import models.Trainer;
import models.Student;
import models.Course;
import models.Assignment;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import models.Database;
import static privateschool.Print.printAllAssignments;
import static privateschool.Print.printAllCourses;
import static privateschool.Print.printAllStudents;
import static privateschool.Print.printAllTrainers;
import static privateschool.Print.printAssignmentsPerCourse;
import static privateschool.Print.printStudentsMoreThanOneCourse;
import static privateschool.Print.printStudentsPerCourse;
import static privateschool.Print.printTrainersPerCourse;

public class MainClass {

    public static List<Course> courses = new ArrayList();
    public static List<Student> students = new ArrayList();
    public static List<Trainer> trainers = new ArrayList();
    public static List<Assignment> assignments = new ArrayList();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static Scanner scanner = new Scanner(System.in);
    public static List<String> inspectMenuOptions = new ArrayList(Arrays.asList(
            "Students\n",//
            "Trainers\n",//
            "Assignments\n",//
            "Courses\n",//
            "Students per course\n",//
            "Trainers per course\n",//
            "Assignments per course\n",//
            "Assignments per student\n",//
            "Students that belong to more than one courses\n",
            "Return to main menu\n"));//


    public static ResultSet getStudentsPerCourse(Database db, Integer id) {
        ResultSet studentsPerCourse = db.getResults(
                "SELECT \n"
                + "students.id,\n"
                + "    students.first_name,\n"
                + "    students.last_name,\n"
                + "    courses.title,\n"
                + "    courses.type,\n"
                + "    courses.stream\n"
                + "FROM\n"
                + "    students,\n"
                + "    students_courses,\n"
                + "    courses\n"
                + "WHERE\n"
                + "    students.id = students_courses.students_id\n"
                + "        AND courses.id = "
                + id + "\n"
                + "GROUP BY students.id;"
        );
        return studentsPerCourse;
    }

    public static ResultSet getTrainersPerCourse(Database db, Integer id) {
        ResultSet trainersPerCourse = db.getResults(
                "SELECT \n"
                + "    trainers.id,\n"
                + "    trainers.first_name,\n"
                + "    trainers.last_name,\n"
                + "    courses.title,\n"
                + "    courses.type,\n"
                + "    courses.stream\n"
                + "FROM\n"
                + "    trainers,\n"
                + "    trainers_courses,\n"
                + "    courses\n"
                + "WHERE\n"
                + "    trainers.id = trainers_courses.trainers_id\n"
                + "        AND courses.id = "
                + +id + "\n"
                + "GROUP BY trainers.id;");
        return trainersPerCourse;
    }

    public static ResultSet getAssignmentsPerCourse(Database db, Integer id) {
        ResultSet assignmentsPerCourse = db.getResults(
                "SELECT \n"
                + "    assignments.id,\n"
                + "    assignments.title,\n"
                + "    assignments.description,\n"
                + "    courses.title,\n"
                + "    courses.type,\n"
                + "    courses.stream\n"
                + "FROM\n"
                + "    assignments,\n"
                + "    assignments_courses,\n"
                + "    courses\n"
                + "WHERE\n"
                + "    assignments.id = assignments_courses.assignments_id\n"
                + "        AND courses.id = "
                + id + "\n"
                + "GROUP BY assignments.id;");
        return assignmentsPerCourse;
    }

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

    public static void main(String[] args) {
//        dummyData();
//        mainMenu();
        Database db = new Database();
//        ResultSet students = db.getResults("SELECT * FROM students");
//        printAllStudents(students);
//        ResultSet courses = db.getResults("SELECT * FROM courses");
//        printAllCourses(courses);
//        ResultSet trainers = db.getResults("SELECT * FROM trainers");
//        printAllTrainers(trainers);
//        ResultSet assignments = db.getResults("SELECT * FROM assignments");
//        printAllAssignments(assignments);
//        int id = 7;
//        ResultSet studentsPerCourse = getStudentsPerCourse(db, id);
//        printStudentsPerCourse(studentsPerCourse);
////        int id = 3;
//        ResultSet trainersPerCourse = getTrainersPerCourse(db, id);
//        printTrainersPerCourse(trainersPerCourse);
////        int id = 3;
//        ResultSet assignmentsPerCourse = getAssignmentsPerCourse(db, id);
//        printAssignmentsPerCourse(assignmentsPerCourse);
//        ResultSet studentsMoreThanOneCourse = getStudentsMoreThanOneCourse(db);
//        printStudentsMoreThanOneCourse(studentsMoreThanOneCourse);

//TODO add only ids to list for selection from user
    }
}
