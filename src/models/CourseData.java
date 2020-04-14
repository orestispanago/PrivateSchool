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

public class CourseData {
//    Database db = new Database();

    private static ResultSet getAll() {
        return Database.getResults("SELECT * FROM courses ORDER BY id");
    }

    public static void printAll() {
        ResultSet rs = getAll();
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-10s|%-14s|%-12s|%-12s|\n";
            String header = String.format(stringFormat,
                    "ID",
                    "Title",
                    "Stream",
                    "Type",
                    "Start date",
                    "End date");
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

    public static int insert(Course course) {
        int result = 0;
        String sql = String.format(
                "INSERT INTO `courses` ("
                + "`title`, "
                + "`stream`, "
                + "`type`,"
                + "`start_date`,"
                + "`end_date`) "
                + "VALUES ('%s', '%s', '%s', '%s', '%s');",
                course.getTitle(),
                course.getStream(),
                course.getType(),
                course.getStart_dateStringSQL(),
                course.getEnd_dateStringSQL());
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
