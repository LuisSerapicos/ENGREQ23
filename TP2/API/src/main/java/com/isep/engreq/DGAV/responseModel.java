package com.isep.engreq.DGAV;

public class responseModel {
    //true false
    private boolean isInsidePortugal;
    private String message;

    public responseModel(boolean isInsidePortugal, String message) {
        this.isInsidePortugal = isInsidePortugal;
        this.message = message;
    }

    public boolean isInsidePortugal() {
        return isInsidePortugal;
    }

    public void setInsidePortugal(boolean insidePortugal) {
        isInsidePortugal = insidePortugal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}