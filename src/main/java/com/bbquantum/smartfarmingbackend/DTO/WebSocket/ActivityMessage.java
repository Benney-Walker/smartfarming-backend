package com.bbquantum.smartfarmingbackend.DTO.WebSocket;

public class ActivityMessage {

    private String timeStamp;

    private String actionType;

    private String message;

    private String status;

    public ActivityMessage(String timeStamp, String actionType, String message, String status) {
        this.timeStamp = timeStamp;
        this.actionType = actionType;
        this.message = message;
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getActionType() {
        return actionType;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
