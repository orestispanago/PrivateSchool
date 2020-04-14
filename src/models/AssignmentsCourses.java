package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import privateschool.MainClass;
import static privateschool.MainClass.dashes;
import static privateschool.MainClass.equalsigns;
import static privateschool.MainClass.scanner;

public class AssignmentsCourses {

        public static boolean idExists(int id) {
        boolean idExists = false;
        try {
            idExists = getAssignmentsPerCourse(id).next();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idExists;
    }

    public static int getId() {
        int num;
        do {
            System.out.print("Please select an id from the list:");
            while (!scanner.hasNextInt()) {
                System.out.print("Please select an id from the list:");
                scanner.next();
            }
            num = scanner.nextInt();
        } while (num < 1 || !idExists(num));
        return num;
    }

    
    private static ResultSet getAssignmentsPerCourse(int id) {
        String sql = String.format(
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
                + "        AND courses.id = '%s'\n"
                + "GROUP BY assignments.id;", id);
        return Database.getResults(sql);
    }

    public static void printAssignmentsPerCourse() {
        int id = getId();
        ResultSet rs = getAssignmentsPerCourse(id);
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-18s|%-18s|%-14s|%-12s|%-6s|\n";
            String header = String.format(stringFormat,
                    "ID",
                    "Title",
                    "Description",
                    "Course title",
                    "Type",
                    "Stream");
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

    private static ResultSet getAssignmentsPerCoursePerStudent(int id) {
        String sql = String.format(
                "    SELECT \n"
                + "students.id,\n"
                + "    students.first_name,\n"
                + "    students.last_name,\n"
                + "    courses.title,\n"
                + "    courses.type,\n"
                + "    courses.stream,\n"
                + "    assignments.title\n"
                + "FROM\n"
                + "    students,\n"
                + "    students_courses,\n"
                + "    courses,\n"
                + "    assignments,\n"
                + "    assignments_courses\n"
                + "WHERE\n"
                + "    students.id = students_courses.students_id\n"
                + "        AND courses.id = assignments_courses.courses_id\n"
                + "        AND students.id = %s\n"
                + "GROUP BY courses.id , assignments.id;", id);
        return Database.getResults(sql);
    }

    public static void printAssignmentsPerCoursePerStudent() {
        int id = getId();
        ResultSet rs = getAssignmentsPerCoursePerStudent(id);
        try {
            System.out.println(dashes);
            String stringFormat = "|%-4s|%-10s|%-11s|%-14s|%-10s|%-6s|%-17s|\n";
            String header = String.format(stringFormat,
                    "ID",
                    "First name",
                    "Last name",
                    "Course title",
                    "Type",
                    "Stream",
                    "Assignment title");
            System.out.print(header);
            System.out.println(equalsigns);
            while (rs.next()) {
                System.out.printf(stringFormat,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
            System.out.println(dashes);
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
