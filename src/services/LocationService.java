package services;

import java.sql.*;

import dao.*;
import model.Location;
import utils.*;

public class LocationService {

    LocationDAO dao = new LocationDAOImpl();

    public void add(String name, String city) throws Exception {
        dao.add(new Location(0, name, city));
    }

    public void view() {

        try {

            Connection c = DBConnection.getConnection();

            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Location");

            System.out.println("\n---- LOCATIONS ----");

            System.out.println("+----+----------------------+------------+--------------+");
            System.out.println("| ID | Location Name        | City       | State        |");
            System.out.println("+----+----------------------+------------+--------------+");

            while(rs.next()){

                System.out.printf("| %-2d | %-20s | %-10s | %-12s |\n",
                        rs.getInt("location_id"),
                        rs.getString("location_name"),
                        rs.getString("city"),
                        rs.getString("state"));
            }

            System.out.println("+----+----------------------+------------+--------------+");

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }
}