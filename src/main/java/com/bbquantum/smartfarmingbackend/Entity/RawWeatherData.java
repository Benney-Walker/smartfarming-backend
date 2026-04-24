package com.bbquantum.smartfarmingbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class RawWeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weatherDataId;

    private String temperature;

    private String airMoisture;

    private String windSpeed;

    private String rainFall;

    private String location;

    private LocalDateTime timeStamp;

    public RawWeatherData() {}

    public RawWeatherData(String temperature, String airMoisture, String windSpeed, String rainFall, String location, LocalDateTime timeStamp) {
        this.temperature = temperature;
        this.airMoisture = airMoisture;
        this.windSpeed = windSpeed;
        this.rainFall = rainFall;
        this.location = location;
        this.timeStamp = timeStamp;
    }

    public int getWeatherDataId() {
        return weatherDataId;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getAirMoisture() {
        return airMoisture;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getRainFall() {
        return rainFall;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
