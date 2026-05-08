package com.bbquantum.smartfarmingbackend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weatherDataId;

    private String location;

    private int rainFallProbability;

    private LocalDateTime timeStamp;

    @OneToOne(mappedBy = "weatherData")
    private PreparedData preparedData;

    public WeatherData() {}

    public WeatherData(String location, int rainFallProbability, LocalDateTime timeStamp) {
        this.location = location;
        this.rainFallProbability = rainFallProbability;
        this.timeStamp = timeStamp;
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

    public int getRainFallProbability() {
        return rainFallProbability;
    }

    public void setRainFallProbability(int rainFallProbability) {
        this.rainFallProbability = rainFallProbability;
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
}
