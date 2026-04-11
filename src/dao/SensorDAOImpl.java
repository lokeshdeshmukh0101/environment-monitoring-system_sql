package dao;

import model.Sensor;
import utils.DBConnection;

import java.sql.*;
import java.util.*;

public class SensorDAOImpl extends BaseDAO implements SensorDAO {

    public void add(Sensor s) throws Exception {
        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Sensor(sensor_name,status) VALUES (?,?)");

        ps.setString(1, s.getSensorName());
        ps.setString(2, s.getStatus());
        ps.executeUpdate();

        close(c, ps, null);
    }

    public List<Sensor> getAll() throws Exception {
        List<Sensor> list = new ArrayList<>();

        Connection c = DBConnection.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Sensor");

        while(rs.next()) {
            list.add(new Sensor(
                    rs.getInt("sensor_id"),
                    rs.getString("sensor_name"),
                    rs.getString("status")
            ));
        }

        close(c, st, rs);
        return list;
    }

    public void update(int id, String status) throws Exception {
        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "UPDATE Sensor SET status=? WHERE sensor_id=?");

        ps.setString(1, status);
        ps.setInt(2, id);
        ps.executeUpdate();

        close(c, ps, null);
    }

    public void delete(int id) throws Exception {
        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Sensor WHERE sensor_id=?");

        ps.setInt(1, id);
        ps.executeUpdate();

        close(c, ps, null);
    }
}