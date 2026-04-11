package model;

public class MaintenanceLog {

    private int sensorId;
    private String maintenanceDate;
    private String maintenanceType;

    // NEW FIELDS
    private String nextMaintenanceDate;
    private String sensorName;
    private String username;

    // CONSTRUCTOR
    public MaintenanceLog(int sensorId, String maintenanceDate, String maintenanceType) {
        this.sensorId = sensorId;
        this.maintenanceDate = maintenanceDate;
        this.maintenanceType = maintenanceType;
    }

    // GETTERS
    public int getSensorId() {
        return sensorId;
    }

    public String getDate() {
        return maintenanceDate;
    }

    public String getType() {
        return maintenanceType;
    }

    public String getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getUsername() {
        return username;
    }

    // SETTERS (IMPORTANT FIX)
    public void setNextMaintenanceDate(String nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getMaintenanceDate() {
        return maintenanceDate;
    }
}