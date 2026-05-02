package com.bbquantum.smartfarmingbackend.DTO.MQTT;

public class Alerts {

    private String fieldId;

    private String sensorName;

    private String message;

    private String timeStamp;

    public Alerts(String fieldId, String sensorName, String message, String timeStamp) {
        this.fieldId = fieldId;
        this.sensorName = sensorName;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {return timeStamp;}
}
