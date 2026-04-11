package services;

import dao.*;
import model.Sensor;
import utils.TableUtil;

import java.util.*;

public class SensorService {

    private SensorDAO dao = new SensorDAOImpl();

    public void add(String name) throws Exception {
        dao.add(new Sensor(0, name, "ACTIVE"));
    }



    public void view() throws Exception {

        List<Sensor> list = dao.getAll();

        TableUtil.title("SENSOR DATA");

        TableUtil.line(3);
        TableUtil.row("ID", "Name", "Status");
        TableUtil.line(3);

        for(Sensor s : list) {
            TableUtil.row(
                    String.valueOf(s.getSensorId()),
                    s.getSensorName(),
                    s.getStatus()
            );
        }

        TableUtil.line(3);
    }

    public void update(int id, String status) throws Exception {
        dao.update(id, status);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }
}