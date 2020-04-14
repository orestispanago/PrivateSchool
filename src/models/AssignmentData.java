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

public class AssignmentData {

    private static ResultSet getAll() {
        return Database.getResults("SELECT * FROM assignments ORDER BY id");
    }

    public static void printAll() {
        ResultSet rs = getAll();
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-20s|%-10s|%-10s|\n";
            String header = String.format(stringFormat,
                    "ID",
                    "Title",
                    "Description",
                    "Submission datetime",
                    "Tot mark");
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

    public static int insert(Assignment assignment) {
        int result = 0;
        String sql = String.format(
                "INSERT INTO `assignments` ("
                + "`title`, "
                + "`description`, "
                + "`submission_datetime`) "
                + "VALUES ('%s', '%s', '%s');",
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getSubDateTimeStringSQL());
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
