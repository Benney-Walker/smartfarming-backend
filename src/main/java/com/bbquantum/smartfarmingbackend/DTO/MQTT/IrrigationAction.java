package com.bbquantum.smartfarmingbackend.DTO.MQTT;

public class IrrigationAction {

    private String actionId;

    private String actionData;

    public IrrigationAction(String actionId, String actionData) {
        this.actionId = actionId;
        this.actionData = actionData;
    }

    public String getActionId() {
        return actionId;
    }

    public String getActionData() {
        return actionData;
    }
}
