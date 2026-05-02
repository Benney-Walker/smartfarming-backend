package com.bbquantum.smartfarmingbackend.DTO.MQTT;

public class ActionFeedBack {

    private String actionId;

    private String actionStatus;

    private String message;

    public ActionFeedBack(String actionId, String actionStatus, String message) {
        this.actionId = actionId;
        this.actionStatus = actionStatus;
        this.message = message;
    }

    public String getActionId() {
        return actionId;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public String getMessage() {
        return message;
    }
}
