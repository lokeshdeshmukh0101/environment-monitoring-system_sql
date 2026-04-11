package dao;

import java.sql.*;

public abstract class BaseDAO {

	protected void close(Connection c, Statement s, ResultSet r) {

	    try {
	        if(r!=null) r.close();
	        if(s!=null) s.close();
	        if(c!=null) c.close();
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}