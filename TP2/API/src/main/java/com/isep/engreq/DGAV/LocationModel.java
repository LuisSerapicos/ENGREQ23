package com.isep.engreq.DGAV;

public class LocationModel {
    private String name;
    private String location;
    private double latitude;
    private double longitude;
    private int apicultorId;

    public LocationModel(String name, String location, double latitude, double longitude, int apicultorId) {
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.apicultorId = apicultorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getApicultorId() {
        return apicultorId;
    }

    public void setApicultorId(int apicultorId) {
        this.apicultorId = apicultorId;
    }
}