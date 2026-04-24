package com.bbquantum.smartfarmingbackend.Entity;

import com.bbquantum.smartfarmingbackend.Contants.DataRange;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProcessedSensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long processedDataId;

    @OneToOne
    @JoinColumn(name = "RawSensorData_rawDataId")
    private RawSensorData rawData;

    private DataRange soilMoisture;

    private DataRange temperature;

    private DataRange humidity;

    private DataRange lightIntensity;

    private DataRange rainFallPossibility;

    private LocalDateTime timeProcessed;

    public ProcessedSensorData() {}

    public ProcessedSensorData(RawSensorData rawData, DataRange soilMoisture, DataRange temperature, DataRange humidity, DataRange lightIntensity, DataRange rainFallPossibility, LocalDateTime timeProcessed) {
        this.rawData = rawData;
        this.soilMoisture = soilMoisture;
        this.temperature = temperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.rainFallPossibility = rainFallPossibility;
        this.timeProcessed = timeProcessed;
    }

    public long getProcessedDataId() {
        return processedDataId;
    }

    public RawSensorData getRawData() {
        return rawData;
    }

    public DataRange getSoilMoisture() {
        return soilMoisture;
    }

    public DataRange getTemperature() {
        return temperature;
    }

    public DataRange getHumidity() {
        return humidity;
    }

    public DataRange getLightIntensity() {
        return lightIntensity;
    }

    public DataRange getRainFallPossibility() {
        return rainFallPossibility;
    }

    public LocalDateTime getTimeProcessed() {
        return timeProcessed;
    }
}
