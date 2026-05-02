package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.ModelDecision;
import jakarta.persistence.*;

@Entity
public class ModelResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int responseId;

    @Enumerated(EnumType.STRING)
    private ModelDecision decision;

    private String duration;

    private String confidence;

    private String decisionReason;

    private String timeStamp;

    @OneToOne
    @JoinColumn
    private PreparedData preparedData;

    @OneToOne
    @JoinColumn
    private IrrigationActions action;

    public ModelResponse() {}

    public ModelResponse(ModelDecision decision, String duration, String confidence, String decisionReason, String timeStamp, PreparedData preparedData,
                         IrrigationActions action) {
        this.decision = decision;
        this.duration = duration;
        this.decisionReason = decisionReason;
        this.timeStamp = timeStamp;
        this.preparedData = preparedData;
        this.action = action;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public ModelDecision getDecision() {
        return decision;
    }

    public void setDecision(ModelDecision decision) {
        this.decision = decision;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public void setDecisionReason(String decisionReason) {
        this.decisionReason = decisionReason;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PreparedData getPreparedData() {
        return preparedData;
    }

    public void setPreparedData(PreparedData preparedData) {
        this.preparedData = preparedData;
    }

    public IrrigationActions getAction() {
        return action;
    }

    public void setAction(IrrigationActions action) {
        this.action = action;
    }
}
