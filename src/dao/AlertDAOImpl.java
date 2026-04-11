package dao;

import model.Alert;
import utils.DBConnection;

import java.sql.*;
import java.util.*;

public class AlertDAOImpl extends BaseDAO implements AlertDAO {

    @Override
    public List<Alert> getAll() throws Exception {

        List<Alert> list = new ArrayList<>();

        Connection c = DBConnection.getConnection();
        Statement st = c.createStatement();

        String sql =
        "SELECT a.alert_id, s.sensor_name, a.alert_message, a.severity, a.status, a.created_at " +
        "FROM Alert a JOIN Sensor s ON a.sensor_id = s.sensor_id";

        ResultSet rs = st.executeQuery(sql);

        while(rs.next()) {

            Alert alert = new Alert(
                    rs.getInt("alert_id"),
                    rs.getString("sensor_name"),
                    rs.getString("alert_message"),
                    rs.getString("severity"),
                    rs.getString("status"),
                    rs.getString("created_at")
            );

            list.add(alert);
        }

        close(c, st, rs);

        return list;
    }
}