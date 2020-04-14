package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import privateschool.MainClass;
import static privateschool.MainClass.dashes;
import static privateschool.MainClass.equalsigns;
import static privateschool.MainClass.scanner;

public class StudentsCourses {

    private static ResultSet getStudentsPerCourse(int id) {
        String sql = String.format(
                "SELECT \n"
                + "    students.id,\n"
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
                + "        AND courses.id = %s\n"
                + "GROUP BY students.id;", id);
        return Database.getResults(sql);
    }

    public static boolean idExists(int id) {
        boolean idExists = false;
        try {
            idExists = getStudentsPerCourse(id).next();
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

    public static void printStudentsPerCourse() {
        int id = getId();
        ResultSet rs = getStudentsPerCourse(id);
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-14s|%-22s|%-14s|%-12s|%-6s|\n";
            String header = String.format(stringFormat,
                    "ID",
                    "First name",
                    "Last name",
                    "Title",
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

    public static ResultSet getStudentsMoreThanOneCourse() {
        String rs = String.format(
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
        return Database.getResults(rs);
    }

    public static void printStudentsMoreThanOneCourse() {
        ResultSet rs = getStudentsMoreThanOneCourse();
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

}
