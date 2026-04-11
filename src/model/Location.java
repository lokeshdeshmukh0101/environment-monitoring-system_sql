package model;

public class Location {

    private int locationId;
    private String locationName;
    private String city;

    public Location(int locationId, String locationName, String city) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.city = city;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getCity() {
        return city;
    }
}