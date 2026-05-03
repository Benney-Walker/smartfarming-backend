package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.Model;
import com.bbquantum.smartfarmingbackend.Contants.ModelDecision;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ModelResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int responseId;

    @Enumerated(EnumType.STRING)
    private ModelDecision decision;

    private int waterQuantity;

    private int confidence;

    private String decisionReason;

    private LocalDateTime timeStamp;

    @Enumerated(EnumType.STRING)
    private Model model;

    @OneToOne
    @JoinColumn
    private PreparedData preparedData;

    @OneToOne
    @JoinColumn
    private IrrigationActions action;

    public ModelResponse() {}

    public ModelResponse(ModelDecision decision, int waterQuantity, int confidence, String decisionReason,
                         LocalDateTime timeStamp, Model model, PreparedData preparedData) {
        this.decision = decision;
        this.waterQuantity = waterQuantity;
        this.confidence = confidence;
        this.decisionReason = decisionReason;
        this.timeStamp = timeStamp;
        this.preparedData = preparedData;
    }

    public int getResponseId() {
        return responseId;
    }

    public ModelDecision getDecision() {
        return decision;
    }

    public void setDecision(ModelDecision decision) {
        this.decision = decision;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public void setWaterQuantity(int waterQuantity) {
        this.waterQuantity = waterQuantity;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public void setDecisionReason(String decisionReason) {
        this.decisionReason = decisionReason;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PreparedData getPreparedData() {
        return preparedData;
    }

    public void setPreparedData(PreparedData preparedData) {
        this.preparedData = preparedData;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public IrrigationActions getAction() {
        return action;
    }

    public void setAction(IrrigationActions action) {
        this.action = action;
    }
}
