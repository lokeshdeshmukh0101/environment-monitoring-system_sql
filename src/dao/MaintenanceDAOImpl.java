package dao;

import model.MaintenanceLog;
import utils.DBConnection;

import java.sql.*;
import java.util.*;

public class MaintenanceDAOImpl extends BaseDAO implements MaintenanceDAO {

    public void add(MaintenanceLog log) throws Exception {

        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Maintenance_Log(sensor_id, maintenance_date, maintenance_type) VALUES (?, ?, ?)");

        ps.setInt(1, log.getSensorId());
        ps.setString(2, log.getDate());
        ps.setString(3, log.getType());

        ps.executeUpdate();

        close(c, ps, null);
    }

    public List<MaintenanceLog> getAll() throws Exception {

        List<MaintenanceLog> list = new ArrayList<>();

        Connection c = DBConnection.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(
        	    "SELECT m.sensor_id, m.maintenance_date, m.maintenance_type, " +
        	    "m.next_maintenance_date, s.sensor_name, u.username " +
        	    "FROM Maintenance_Log m " +
        	    "JOIN Sensor s ON m.sensor_id = s.sensor_id " +
        	    "JOIN User u ON m.user_id = u.user_id"
        	);

        while(rs.next()) {
        	MaintenanceLog log = new MaintenanceLog(
        		    rs.getInt("sensor_id"),
        		    rs.getString("maintenance_date"),
        		    rs.getString("maintenance_type")
        		);

        		log.setNextMaintenanceDate(rs.getString("next_maintenance_date"));
        		log.setSensorName(rs.getString("sensor_name"));
        		log.setUsername(rs.getString("username"));

        		list.add(log);
        }

        close(c, st, rs);
        return list;

    }
}
 