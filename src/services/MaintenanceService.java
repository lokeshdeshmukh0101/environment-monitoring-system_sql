package services;

import dao.*;
import model.MaintenanceLog;
import utils.TableUtil;

import java.util.*;

public class MaintenanceService {

    private MaintenanceDAO dao = new MaintenanceDAOImpl();

    // FIXED METHOD (3 PARAMETERS)
    public void add(int sensorId, String date, String type) throws Exception {
        dao.add(new MaintenanceLog(sensorId, date, type));
    }


    public void view() throws Exception {

        List<MaintenanceLog> list = dao.getAll();

        TableUtil.title("MAINTENANCE LOG");

        TableUtil.line(4);
        TableUtil.row("Sensor", "User", "Service Date", "Next Service");
        TableUtil.line(4);

        for (MaintenanceLog m : list) {
            TableUtil.row(
                    m.getSensorName(),
                    m.getUsername(),
                    m.getMaintenanceDate(),
                    m.getNextMaintenanceDate()
            );
        }

        TableUtil.line(4);
    }
}