package dao;

import model.User;

import utils.DBConnection;

import java.sql.*;
import java.util.*;

public class UserDAOImpl extends BaseDAO implements UserDAO {

    public void add(User user) throws Exception {

        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO User(username, role) VALUES (?, ?)");

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getRole());
        ps.executeUpdate();

        close(c, ps, null);
    }
    public void delete(int id) throws Exception {

        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM User WHERE user_id=?");

        ps.setInt(1, id);
        ps.executeUpdate();

        close(c, ps, null);
    }
    public void updateRole(int id,String role) throws Exception {

        Connection c = DBConnection.getConnection();

        PreparedStatement ps = c.prepareStatement(
            "UPDATE User SET role=? WHERE user_id=?");

        ps.setString(1,role);
        ps.setInt(2,id);

        ps.executeUpdate();

        close(c,ps,null);
    }

    public List<User> getAll() throws Exception {

        List<User> list = new ArrayList<>();

        Connection c = DBConnection.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM User");

        while (rs.next()) {
            list.add(new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("role")
            ));
        }

        close(c, st, rs);
        return list;
    }
}