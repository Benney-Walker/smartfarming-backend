package com.bbquantum.smartfarmingbackend.DTO.MQTT;

public class SensorData {

    private String farmId;

    private String soilMoisture;

    private String soilTemperature;

    private String humidity;

    private String lightIntensity;

    public SensorData(String farmId, String soilMoisture, String soilTemperature, String humidity, String lightIntensity) {
        this.farmId = farmId;
        this.soilMoisture = soilMoisture;
        this.soilTemperature = soilTemperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public String getSoilTemperature() {
        return soilTemperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }
}
