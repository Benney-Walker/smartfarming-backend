package com.bbquantum.smartfarmingbackend.DTO.WeatherDTO;

import java.time.LocalDateTime;

public class ReceivedForcast {

    private String location;

    private int rainFallProbability;

    private LocalDateTime timeStamp;

    public ReceivedForcast(String location, int rainFallProbability, LocalDateTime timeStamp) {
        this.location = location;
        this.rainFallProbability = rainFallProbability;
        this.timeStamp = timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public int getRainFallProbability() {
        return rainFallProbability;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
