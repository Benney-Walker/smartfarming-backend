package com.bbquantum.smartfarmingbackend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RawSensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rawDataId;

    @ManyToOne
    @JoinColumn(name = "Fields_fieldId")
    private Fields field;

    private String soilMoisture;

    private String temperature;

    private String humidity;

    private String lightIntensity;

    private LocalDateTime timeOfArrival;

    @OneToOne(mappedBy = "processedDataId")
    private ProcessedSensorData processedSensorData;

    public RawSensorData() {}

    public RawSensorData(Fields field, String soilMoisture, String temperature, String humidity, String lightIntensity, LocalDateTime timeOfArrival, ProcessedSensorData processedSensorData) {
        this.field = field;
        this.soilMoisture = soilMoisture;
        this.temperature = temperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.timeOfArrival = timeOfArrival;
        this.processedSensorData = processedSensorData;
    }

    public long getRawDataId() {
        return rawDataId;
    }

    public Fields getField() {
        return field;
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }

    public LocalDateTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public ProcessedSensorData getProcessedSensorData() {
        return processedSensorData;
    }
}
