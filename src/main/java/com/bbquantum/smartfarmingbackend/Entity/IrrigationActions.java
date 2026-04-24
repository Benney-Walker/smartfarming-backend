package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.ActionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class IrrigationActions {

    @Id
    private String actionId;

    @OneToOne
    @JoinColumn(name = "ModelDecisions_decisionId")
    private ModelDecisions decision;

    @Column(length = 1000, nullable = false)
    private String actionData;

    private LocalDateTime actionTime;

    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    public IrrigationActions() {}

    public IrrigationActions(String actionId, ModelDecisions decision, String actionData, LocalDateTime actionTime, ActionStatus actionStatus) {
        this.actionId = actionId;
        this.decision = decision;
        this.actionData = actionData;
        this.actionTime = actionTime;
        this.actionStatus = actionStatus;
    }

    public String getActionId() {
        return actionId;
    }

    public ModelDecisions getDecision() {
        return decision;
    }

    public String getActionData() {
        return actionData;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public ActionStatus getActionStatus() {
        return actionStatus;
    }
}
