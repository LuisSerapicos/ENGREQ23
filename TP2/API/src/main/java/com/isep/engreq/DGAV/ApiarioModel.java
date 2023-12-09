package com.isep.engreq.DGAV;

public class ApiarioModel {
    private String name;
    private Long latitude;
    private Long longitude;
    private int apicultorId;

    public ApiarioModel(String name, Long latitude, Long longitude, int apicultorId) {
        this.name = name;
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

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public int getApicultorId() {
        return apicultorId;
    }

    public void setApicultorId(int apicultorId) {
        this.apicultorId = apicultorId;
    }
}