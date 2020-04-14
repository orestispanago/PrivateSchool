package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static models.Database.setStatement;
import privateschool.MainClass;
import static privateschool.MainClass.dashes;
import static privateschool.MainClass.db;
import static privateschool.MainClass.equalsigns;

public class TrainerData {
    private static Statement statement = null;

    private static ResultSet getAll() {
        return Database.getResults("SELECT * FROM trainers ORDER BY id");
    }

    public static void printAll() {
        ResultSet rs = getAll();
        try {
            System.out.println(dashes);
            String stringFormat = "|%-5s|%-20s|%-29s|%-21s|\n";
            String header = String.format(stringFormat,
                    "ID",
                    "First name",
                    "Last name",
                    "Subject");
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

    public static int insert(Trainer trainer) {
        int result = 0;
        String sql = String.format(
                "INSERT INTO `trainers` ("
                + "`first_name`, "
                + "`last_name`, "
                + "`subject`) "
                + "VALUES ('%s', '%s', '%s');",
                trainer.getFirstName(),
                trainer.getLastName(),
                trainer.getSubject());
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

        public static ResultSet getObjectRow(Trainer trainer) {
        try {
            setStatement();
            String query = String.format("SELECT * FROM trainers WHERE first_name='"+
                    trainer.getFirstName()+"' and `last_name`='"+
                    trainer.getLastName()+"' and `subject`='"
                    +trainer.getSubject()+"';"
              );
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(TrainerData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int getID(Trainer trainer) {
        int id = 0;
        try {
            
            ResultSet rs = getObjectRow(trainer);
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(TrainerData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }


}
