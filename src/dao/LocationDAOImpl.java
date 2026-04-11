package dao;

import model.Location;
import utils.DBConnection;

import java.sql.*;
import java.util.*;

public class LocationDAOImpl extends BaseDAO implements LocationDAO {

    public void add(Location l) throws Exception {
        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Location(location_name,city) VALUES (?,?)");

        ps.setString(1, l.getLocationName());
        ps.setString(2, l.getCity());
        ps.executeUpdate();

        close(c, ps, null);
    }

    public List<Location> getAll() throws Exception {
        List<Location> list = new ArrayList<>();

        Connection c = DBConnection.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Location");

        while(rs.next()) {
            list.add(new Location(
                    rs.getInt("location_id"),
                    rs.getString("location_name"),
                    rs.getString("city")
            ));
        }

        close(c, st, rs);
        return list;
    }

    public void delete(int id) throws Exception {
        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Location WHERE location_id=?");

        ps.setInt(1, id);
        ps.executeUpdate();

        close(c, ps, null);
    }
}