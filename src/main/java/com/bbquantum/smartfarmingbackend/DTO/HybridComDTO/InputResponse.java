package com.bbquantum.smartfarmingbackend.DTO.HybridComDTO;

public class InputResponse {

    private String decision; // it can Irrigate, no_irrigation, Delay

    private int waterQuantity; // In liters

    private int confidence; // In percentage

    private String decisionReason;

    private String preparedDataId;

    public InputResponse(String decision, int waterQuantity, int confidence, String decisionReason, String preparedDataId) {
        this.decision = decision;
        this.waterQuantity = waterQuantity;
        this.confidence = confidence;
        this.decisionReason = decisionReason;
        this.preparedDataId = preparedDataId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
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

    public String getPreparedDataId() {
        return preparedDataId;
    }

    public void setPreparedDataId(String preparedDataId) {
        this.preparedDataId = preparedDataId;
    }
}
