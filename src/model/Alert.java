package model;

public class Alert {

    private int alertId;
    private int sensorId;
    private String message;
    private String sensorName;
    private String severity;
    private String createdAt;
    private String status;

    public Alert(int id, String sensorName, String message, String severity, String status, String createdAt) {
        this.alertId = id;
        this.sensorName = sensorName;
        this.message = message;
        this.severity = severity;
        this.status = status;
        this.createdAt = createdAt;
    }
    public String getSensorName() {
        return sensorName;
    }

    public String getSeverity() {
        return severity;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getAlertId() { return alertId; }
    public int getSensorId() { return sensorId; }
    public String getMessage() { return message; }
}