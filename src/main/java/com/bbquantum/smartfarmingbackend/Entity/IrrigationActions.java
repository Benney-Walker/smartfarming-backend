package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.ActionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class IrrigationActions {

    @Id
    private String actionId;

    @Column(length = 1000, nullable = false)
    private String actionData;

    private LocalDateTime actionTime;

    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    @Column(length = 1000)
    private String message;

    @OneToOne(mappedBy = "action")
    private ModelResponse modelResponse;

    public IrrigationActions() {}

    public IrrigationActions(String actionId, String actionData, LocalDateTime actionTime, ActionStatus actionStatus, String message, ModelResponse modelResponse) {
        this.actionId = actionId;
        this.actionData = actionData;
        this.actionTime = actionTime;
        this.actionStatus = actionStatus;
        this.message = message;
        this.modelResponse = modelResponse;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionData() {
        return actionData;
    }

    public void setActionData(String actionData) {
        this.actionData = actionData;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModelResponse getModelResponse() {
        return modelResponse;
    }

    public void setModelResponse(ModelResponse modelResponse) {
        this.modelResponse = modelResponse;
    }
}
