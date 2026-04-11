package dao;

import model.Sensor;

import java.util.*;

public interface SensorDAO {
    void add(Sensor s) throws Exception;
    List<Sensor> getAll() throws Exception;
    void update(int id, String status) throws Exception;
    void delete(int id) throws Exception;
}