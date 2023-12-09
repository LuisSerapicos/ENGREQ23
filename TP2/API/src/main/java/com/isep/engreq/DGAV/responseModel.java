package com.isep.engreq.DGAV;

public class responseModel {
    //true false
    private boolean value;
    private String message;


    public responseModel(boolean value, String message) {
        this.value = value;
        this.message = message;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}