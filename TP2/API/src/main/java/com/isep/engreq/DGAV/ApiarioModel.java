package com.isep.engreq.DGAV;

public class ApiarioModel {
    private int id;
    private String name;
    private String location;
    private double latitude;
    private double longitude;
    private int apicultorId;

    public ApiarioModel(int id, String name, String location, double latitude, double longitude, int apicultorId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.apicultorId = apicultorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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