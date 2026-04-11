package model;

public class Sensor {

    private int sensorId;
    private String sensorName;
    private String status;

    public Sensor(int id, String name, String status) {
        this.sensorId = id;
        this.sensorName = name;
        this.status = status;
    }

    public int getSensorId() { return sensorId; }
    public String getSensorName() { return sensorName; }
    public String getStatus() { return status; }
}