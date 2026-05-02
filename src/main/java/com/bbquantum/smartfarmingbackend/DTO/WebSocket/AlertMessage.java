package com.bbquantum.smartfarmingbackend.DTO.WebSocket;

public class AlertMessage {

    private String timeStamp;

    private String fieldName;

    private String alertMessage;

    public AlertMessage(String timeStamp, String fieldName, String alertMessage) {
        this.timeStamp = timeStamp;
        this.fieldName = fieldName;
        this.alertMessage = alertMessage;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }
}
