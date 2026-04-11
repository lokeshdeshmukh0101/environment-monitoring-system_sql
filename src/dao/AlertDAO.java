package dao;

import model.Alert;

import java.util.List;

public interface AlertDAO {
    List<Alert> getAll() throws Exception;
}