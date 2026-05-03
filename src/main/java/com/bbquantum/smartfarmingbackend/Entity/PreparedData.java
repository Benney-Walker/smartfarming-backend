package com.bbquantum.smartfarmingbackend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PreparedData {

    @Id
    private String preparedDataId;

    private String soilMoisture;

    private String soilTemperature;

    private String humidity;

    private String lightIntensity;

    private String rainFallPossibility;

    private LocalDateTime timeProcessed;

    @OneToOne
    @JoinColumn
    private SensorData sensorData;

    @OneToOne
    @JoinColumn
    private WeatherData weatherData;

    private boolean isDecisionMade;

    @OneToOne(mappedBy = "preparedData")
    private ModelResponse modelResponse;

    public PreparedData() {}

    public PreparedData(String preparedDataId, String soilMoisture, String soilTemperature, String humidity,
                        String lightIntensity, String rainFallPossibility, LocalDateTime timeProcessed,
                        SensorData sensorData, WeatherData weatherData, boolean isDecisionMade) {
        this.preparedDataId = preparedDataId;
        this.soilMoisture = soilMoisture;
        this.soilTemperature = soilTemperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.rainFallPossibility = rainFallPossibility;
        this.timeProcessed = timeProcessed;
        this.sensorData = sensorData;
        this.weatherData = weatherData;
        this.isDecisionMade = isDecisionMade;
    }

    public String getPreparedDataId() {
        return preparedDataId;
    }

    public void setPreparedDataId(String preparedDataId) {
        this.preparedDataId = preparedDataId;
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(String soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public String getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(String soilTemperature) {
        this.soilTemperature = soilTemperature;
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

    public String getRainFallPossibility() {
        return rainFallPossibility;
    }

    public void setRainFallPossibility(String rainFallPossibility) {
        this.rainFallPossibility = rainFallPossibility;
    }

    public LocalDateTime getTimeProcessed() {
        return timeProcessed;
    }

    public void setTimeProcessed(LocalDateTime timeProcessed) {
        this.timeProcessed = timeProcessed;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public boolean isDecisionMade() {
        return isDecisionMade;
    }

    public void setDecisionMade(boolean decisionMade) {
        isDecisionMade = decisionMade;
    }

    public ModelResponse getModelResponse() {
        return modelResponse;
    }

    public void setModelResponse(ModelResponse modelResponse) {
        this.modelResponse = modelResponse;
    }
}
