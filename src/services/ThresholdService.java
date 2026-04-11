package services;

import utils.DBConnection;
import java.sql.*;

import dao.ThresholdDao;
import dao.ThresholdDAOImpl;

public class ThresholdService {

    // DAO object
    private ThresholdDao dao = new ThresholdDAOImpl();

    // VIEW THRESHOLD TABLE
    public void view() {

        try {
            Connection c = DBConnection.getConnection();

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Threshold");

            System.out.println("\n---- THRESHOLD DATA ----");

            System.out.println("+----+--------------+----------+----------+");
            System.out.println("| ID | Parameter    | Min      | Max      |");
            System.out.println("+----+--------------+----------+----------+");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;

                System.out.printf("| %-2d | %-12s | %-8.2f | %-8.2f |\n",
                        rs.getInt("threshold_id"),
                        rs.getString("parameter_name"),
                        rs.getDouble("min_value"),
                        rs.getDouble("max_value"));
            }

            if (!hasData) {
                System.out.println("⚠ No threshold data found in database.");
            }

            System.out.println("+----+--------------+----------+----------+");

            rs.close();
            st.close();
            c.close();

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
        }
    }

    // UPDATE THRESHOLD VALUES
    public void update(int id, double min, double max) {

        try {

            dao.update(id, min, max);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
        }
    }
}