package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.Anomaly;
import com.bbquantum.smartfarmingbackend.Contants.Actions;
import com.bbquantum.smartfarmingbackend.Contants.Model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ModelDecisions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long decisionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fields_fieldId")
    private Fields field;

    private LocalDateTime decisionTime;

    @Enumerated(EnumType.STRING)
    private Actions action;

    private String decisionMessage;

    private String decisionReason;

    @Enumerated(EnumType.STRING)
    private Anomaly anomaly;

    @Enumerated(EnumType.STRING)
    private Model decisionModel;

    @OneToOne(mappedBy = "decision")
    private IrrigationActions irrigationAction;

    public ModelDecisions() {}

    public ModelDecisions(Fields field, LocalDateTime decisionTime, Actions action, String decisionMessage, String decisionReason, Anomaly anomaly, Model decisionModel, IrrigationActions irrigationAction) {
        this.field = field;
        this.decisionTime = decisionTime;
        this.action = action;
        this.decisionMessage = decisionMessage;
        this.decisionReason = decisionReason;
        this.anomaly = anomaly;
        this.decisionModel = decisionModel;
        this.irrigationAction = irrigationAction;
    }

    public long getDecisionId() {
        return decisionId;
    }

    public Fields getField() {
        return field;
    }

    public LocalDateTime getDecisionTime() {
        return decisionTime;
    }

    public Actions getAction() {
        return action;
    }

    public String getDecisionMessage() {
        return decisionMessage;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public Anomaly getAnomaly() {
        return anomaly;
    }

    public Model getDecisionModel() {
        return decisionModel;
    }

    public IrrigationActions getIrrigationAction() {
        return irrigationAction;
    }
}
