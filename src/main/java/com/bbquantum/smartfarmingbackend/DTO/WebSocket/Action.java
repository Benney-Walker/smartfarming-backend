package com.bbquantum.smartfarmingbackend.DTO.WebSocket;

public class Action {

    private String timeStamp;

    private String field;

    private String command;

    private String triggerSource;

    public Action(String timeStamp, String field, String command, String triggerSource) {
        this.timeStamp = timeStamp;
        this.field = field;
        this.command = command;
        this.triggerSource = triggerSource;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getField() {
        return field;
    }

    public String getCommand() {
        return command;
    }

    public String getTriggerSource() {
        return triggerSource;
    }
}
