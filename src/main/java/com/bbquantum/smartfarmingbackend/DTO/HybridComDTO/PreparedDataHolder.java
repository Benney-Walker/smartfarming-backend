package com.bbquantum.smartfarmingbackend.DTO.HybridComDTO;

public class PreparedDataHolder {
    private String preparedDataId;

    private double soilMoisture;

    private double soilTemperature;

    private double humidity;

    private double lightIntensity;

    private double rainProbability;

    public PreparedDataHolder(String preparedDataId, double soilMoisture, double soilTemperature, double humidity,
                              double lightIntensity, double rainProbability) {
        this.preparedDataId = preparedDataId;
        this.soilMoisture = soilMoisture;
        this.soilTemperature = soilTemperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.rainProbability = rainProbability;
    }

    public String getPreparedDataId() {
        return preparedDataId;
    }

    public void setPreparedDataId(String preparedDataId) {
        this.preparedDataId = preparedDataId;
    }

    public double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public double getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(double soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(double lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public double getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(double rainProbability) {
        this.rainProbability = rainProbability;
    }
}
