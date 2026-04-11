package dao;

import model.Location;
import java.util.List;

public interface LocationDAO {

    void add(Location l) throws Exception;

    List<Location> getAll() throws Exception;

    void delete(int id) throws Exception;
}