package services;

import utils.DBConnection;

import java.sql.*;

public class ReadingService {

    public void add(int sid, double val) throws Exception {

        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Sensor_Reading(sensor_id,reading_value) VALUES (?,?)");

        ps.setInt(1, sid);
        ps.setDouble(2, val);
        ps.executeUpdate();

        c.close();
    }

    public void view() {

        try {

            Connection c = DBConnection.getConnection();

            String sql = """
            SELECT s.sensor_name,
                   r.reading_value,
                   r.reading_timestamp
            FROM Sensor_Reading r
            JOIN Sensor s ON r.sensor_id = s.sensor_id
            """;

            ResultSet rs = c.createStatement().executeQuery(sql);

            System.out.println("\n---- SENSOR READINGS ----");

            System.out.println("+----------------+--------------+---------------------+");
            System.out.println("| Sensor         | Reading      | Time                |");
            System.out.println("+----------------+--------------+---------------------+");

            while(rs.next()){

                System.out.printf("| %-14s | %-12.2f | %-19s |\n",
                        rs.getString("sensor_name"),
                        rs.getDouble("reading_value"),
                        rs.getString("reading_timestamp")
                );
            }

            System.out.println("+----------------+--------------+---------------------+");

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}