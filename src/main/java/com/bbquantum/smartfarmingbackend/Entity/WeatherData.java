package com.bbquantum.smartfarmingbackend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weatherDataId;

    private String location;

    private String rainFallProbability;

    private String timeStamp;

    private boolean isProcessed;

    @OneToOne(mappedBy = "weatherData")
    private PreparedData preparedData;

    public WeatherData() {}

    public WeatherData(String location, String rainFallProbability, String timeStamp, boolean isProcessed, PreparedData preparedData) {
        this.location = location;
        this.rainFallProbability = rainFallProbability;
        this.timeStamp = timeStamp;
        this.isProcessed = isProcessed;
        this.preparedData = preparedData;
    }

    public int getWeatherDataId() {
        return weatherDataId;
    }

    public void setWeatherDataId(int weatherDataId) {
        this.weatherDataId = weatherDataId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRainFallProbability() {
        return rainFallProbability;
    }

    public void setRainFallProbability(String rainFallProbability) {
        this.rainFallProbability = rainFallProbability;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public PreparedData getPreparedData() {
        return preparedData;
    }

    public void setPreparedData(PreparedData preparedData) {
        this.preparedData = preparedData;
    }
}
