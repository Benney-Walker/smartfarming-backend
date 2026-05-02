package com.bbquantum.smartfarmingbackend.DTO.HybridComDTO;

public class InputResponse {

    private String decision;

    private String duration;

    private String confidence;

    private String decisionReason;

    private String timeStamp;

    private PreparedDataHolder inputs;

    public InputResponse(String decision, String duration, String confidence, String decisionReason, String timeStamp, PreparedDataHolder inputs) {
        this.decision = decision;
        this.duration = duration;
        this.confidence = confidence;
        this.decisionReason = decisionReason;
        this.timeStamp = timeStamp;
        this.inputs = inputs;
    }

    public String getDecision() {
        return decision;
    }

    public String getDuration() {
        return duration;
    }

    public String getConfidence() {
        return confidence;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public PreparedDataHolder getInputs() {
        return inputs;
    }
}
