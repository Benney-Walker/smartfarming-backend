package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.ActionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class IrrigationActions {

    @Id
    private String actionId;

    private int waterQuantity;

    private LocalDateTime actionTime;

    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    @OneToOne(mappedBy = "action")
    private ModelResponse modelResponse;

    public IrrigationActions() {}

    public IrrigationActions(String actionId, int waterQuantity, LocalDateTime actionTime, ActionStatus actionStatus,
                             ModelResponse modelResponse) {
        this.actionId = actionId;
        this.waterQuantity = waterQuantity;
        this.actionTime = actionTime;
        this.actionStatus = actionStatus;
        this.modelResponse = modelResponse;
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

    public void setWaterQuantity(int waterQuantity) {
        this.waterQuantity = waterQuantity;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public ActionStatus getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(ActionStatus actionStatus) {
        this.actionStatus = actionStatus;
    }

    public ModelResponse getModelResponse() {
        return modelResponse;
    }

    public void setModelResponse(ModelResponse modelResponse) {
        this.modelResponse = modelResponse;
    }
}
