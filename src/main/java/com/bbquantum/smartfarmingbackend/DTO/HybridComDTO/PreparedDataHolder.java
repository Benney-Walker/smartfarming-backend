package com.bbquantum.smartfarmingbackend.DTO.HybridComDTO;

public class PreparedDataHolder {
    private int preparedDataId;

    private String soilMoisture;

    private String soilTemperature;

    private String humidity;

    private String lightIntensity;

    private String rainProbability;

    public PreparedDataHolder(int preparedDataId, String soilMoisture, String soilTemperature, String humidity,
                              String lightIntensity, String rainProbability) {
        this.preparedDataId = preparedDataId;
        this.soilMoisture = soilMoisture;
        this.soilTemperature = soilTemperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.rainProbability = rainProbability;
    }

    public int getPreparedDataId() {
        return preparedDataId;
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

    public String getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(String rainProbability) {
        this.rainProbability = rainProbability;
    }
}
