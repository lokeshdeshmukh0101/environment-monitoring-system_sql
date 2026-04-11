package dao;

import model.MaintenanceLog;
import java.util.List;

public interface MaintenanceDAO {

    void add(MaintenanceLog log) throws Exception;

    List<MaintenanceLog> getAll() throws Exception;
}