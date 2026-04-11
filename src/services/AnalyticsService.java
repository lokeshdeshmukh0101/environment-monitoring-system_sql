package services;

import utils.DBConnection;
import utils.TableUtil;

import java.sql.*;

public class AnalyticsService {

    // 1. Sensors per location
	public void sensorsPerLocation() {

	    try {

	        Connection c = DBConnection.getConnection();

	        ResultSet rs = c.createStatement().executeQuery("""
	        SELECT l.location_name, COUNT(*) AS total
	        FROM Sensor s
	        JOIN Location l ON s.location_id = l.location_id
	        GROUP BY l.location_name
	        """);

	        TableUtil.title("SENSORS BY LOCATION");

	        TableUtil.line(2);
	        TableUtil.row("Location","Sensors");
	        TableUtil.line(2);

	        while(rs.next()) {
	            TableUtil.row(
	                rs.getString("location_name"),
	                rs.getString("total")
	            );
	        }

	        TableUtil.line(2);

	    } catch(Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

    // 2. Sensor count by type
    public void sensorsByType() {

        try {
            Connection c = DBConnection.getConnection();

            ResultSet rs = c.createStatement().executeQuery("""
            SELECT t.type_name, COUNT(*) AS total
            FROM Sensor s
            JOIN Sensor_Type t ON s.type_id = t.type_id
            GROUP BY t.type_name
            """);

            TableUtil.title("SENSORS BY TYPE");

            TableUtil.line(2);
            TableUtil.row("Type","Total Sensors");
            TableUtil.line(2);

            while(rs.next()) {
                TableUtil.row(
                    rs.getString("type_name"),
                    rs.getString("total")
                );
            }

            TableUtil.line(2);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Recent alerts
    public void recentAlerts() {

        try {
            Connection c = DBConnection.getConnection();

            String sql = """
            SELECT a.alert_id,
                   s.sensor_name,
                   a.alert_message,
                   a.severity,
                   a.created_at
            FROM Alert a
            JOIN Sensor s ON a.sensor_id = s.sensor_id
            ORDER BY a.created_at DESC
            LIMIT 10
            """;

            ResultSet rs = c.createStatement().executeQuery(sql);

            TableUtil.title("RECENT ALERTS");

            TableUtil.line(5);
            TableUtil.row("ID","Sensor","Message","Severity","Time");
            TableUtil.line(5);

            while(rs.next()) {
                TableUtil.row(
                    rs.getString("alert_id"),
                    rs.getString("sensor_name"),
                    rs.getString("alert_message"),
                    rs.getString("severity"),
                    rs.getString("created_at")
                );
            }

            TableUtil.line(5);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 4. Average sensor readings
    public void avgReadings() {
        try {
            Connection c = DBConnection.getConnection();

            String sql = """
                SELECT s.sensor_name,
                       ROUND(AVG(r.reading_value),2) AS avg_reading
                FROM Sensor_Reading r
                JOIN Sensor s ON r.sensor_id = s.sensor_id
                GROUP BY s.sensor_name
            """;

            ResultSet rs = c.createStatement().executeQuery(sql);

            TableUtil.title("AVERAGE SENSOR READINGS");

            TableUtil.line(2);
            TableUtil.row("Sensor", "Average");
            TableUtil.line(2);

            while (rs.next()) {
                TableUtil.row(
                    rs.getString("sensor_name"),
                    rs.getString("avg_reading")
                );
            }

            TableUtil.line(2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void maintenanceSchedule() {

        try {

            Connection c = DBConnection.getConnection();

            String sql = """
                SELECT s.sensor_name,
                       m.maintenance_date,
                       m.next_maintenance_date
                FROM Maintenance_Log m
                JOIN Sensor s ON m.sensor_id = s.sensor_id
                ORDER BY m.next_maintenance_date
            """;

            ResultSet rs = c.createStatement().executeQuery(sql);

            TableUtil.title("MAINTENANCE SCHEDULE");

            TableUtil.line(3);
            TableUtil.row("Sensor","Last Service","Next Service");
            TableUtil.line(3);

            while(rs.next()) {

                TableUtil.row(
                    rs.getString("sensor_name"),
                    rs.getString("maintenance_date"),
                    rs.getString("next_maintenance_date")
                );
            }

            TableUtil.line(3);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}