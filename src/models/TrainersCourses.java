package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import privateschool.MainClass;
import static privateschool.MainClass.dashes;
import static privateschool.MainClass.db;
import static privateschool.MainClass.equalsigns;
import static privateschool.MainClass.scanner;

public class TrainersCourses {

    public static boolean idExists(int id) {
        boolean idExists = false;
        try {
            idExists = getTrainersPerCourse(id).next();
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

    private static ResultSet getTrainersPerCourse(int id) {
        String sql = String.format(
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
                + "        AND courses.id = %s\n"
                + "GROUP BY trainers.id;", id);
        return Database.getResults(sql);
    }

    public static void printTrainersPerCourse() {
        int id = getId();
        ResultSet rs = getTrainersPerCourse(id);
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


    public static int trainerToCourse(int trainer_id) {
        System.out.println("Add trainer to course");
            CourseData.printAll();
            int course_id = getId();
        int result = 0;
        String sql = String.format(
                "INSERT INTO `trainers_courses` ("
                + "`trainers_id`, "
                + "`courses_id`,"
                + "VALUES ('%s', '%s');",
                trainer_id, course_id);
        db.setStatementNonStatic();
        Statement st = db.getStatementNonStatic();
        try {
            result = st.executeUpdate(sql);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }

    }
}
