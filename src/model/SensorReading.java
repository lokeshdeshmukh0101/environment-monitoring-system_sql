package model;

public class SensorReading {

    private int sensorId;
    private double value;

    public SensorReading(int sid, double val) {
        this.sensorId = sid;
        this.value = val;
    }

    public int getSensorId() { return sensorId; }
    public double getValue() { return value; }
}