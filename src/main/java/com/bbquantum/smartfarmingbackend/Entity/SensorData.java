package com.bbquantum.smartfarmingbackend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sensorDataId;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Fields field;

    @Column(nullable = false)
    private String soilMoisture;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private String humidity;

    @Column(nullable = false)
    private String lightIntensity;

    @Column(nullable = false)
    private LocalDateTime timeOfArrival;

    private boolean isProcessed;

    @OneToOne(mappedBy = "sensorData")
    private PreparedData preparedData;

    public SensorData() {}

    public SensorData(Fields field, String soilMoisture, String temperature, String humidity, String lightIntensity,
                      LocalDateTime timeOfArrival, boolean isProcessed) {
        this.field = field;
        this.soilMoisture = soilMoisture;
        this.temperature = temperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.timeOfArrival = timeOfArrival;
        this.isProcessed = isProcessed;
    }

    public Fields getField() {
        return field;
    }

    public void setField(Fields field) {
        this.field = field;
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(String soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public LocalDateTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(LocalDateTime timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
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
