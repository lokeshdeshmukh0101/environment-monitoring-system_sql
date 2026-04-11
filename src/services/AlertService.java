package services;

import dao.*;
import model.Alert;
import utils.TableUtil;

import java.util.*;

public class AlertService {

    private AlertDAO dao = new AlertDAOImpl();

    public void view() throws Exception {

        List<Alert> list = dao.getAll();

        TableUtil.title("ALERTS");

        TableUtil.line(5);
        TableUtil.row("Alert ID","Sensor","Message","Severity","Time");
        TableUtil.line(5);

        for (Alert a : list) {

            TableUtil.row(
                    String.valueOf(a.getAlertId()),
                    a.getSensorName(),     // sensor name instead of id
                    a.getMessage(),
                    a.getSeverity(),
                    a.getCreatedAt()       // alert time
            );
        }

        TableUtil.line(5);
    }
}