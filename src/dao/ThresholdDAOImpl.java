package dao;

import model.Threshold;

import utils.DBConnection;

import java.sql.*;
import java.util.*;

public class ThresholdDAOImpl extends BaseDAO implements ThresholdDao{
	public void update(int id, double min, double max) throws Exception {

	    Connection c = DBConnection.getConnection();

	    PreparedStatement ps = c.prepareStatement(
	        "UPDATE Threshold SET min_value=?, max_value=? WHERE threshold_id=?");

	    ps.setDouble(1, min);
	    ps.setDouble(2, max);
	    ps.setInt(3, id);

	    ps.executeUpdate();

	    close(c, ps, null);
	}
}
