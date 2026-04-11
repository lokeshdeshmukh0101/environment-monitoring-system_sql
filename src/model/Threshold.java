package model;

public class Threshold {

    private int thresholdId;
    private String parameterName;
    private double minValue;
    private double maxValue;

    public Threshold(int id, String name, double min, double max) {
        this.thresholdId = id;
        this.parameterName = name;
        this.minValue = min;
        this.maxValue = max;
    }

    public int getThresholdId() {
        return thresholdId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }
}
