package com.bbquantum.smartfarmingbackend.DTO.MQTT;

public class IrrigationAction {

    private String actionId;

    private int waterQuantity;

    public IrrigationAction(String actionId, int waterQuantity) {
        this.actionId = actionId;
        this.waterQuantity = waterQuantity;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }
}
