package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.PreparedDataHolder;
import com.bbquantum.smartfarmingbackend.Entity.Fields;
import com.bbquantum.smartfarmingbackend.Entity.PreparedData;
import com.bbquantum.smartfarmingbackend.Entity.SensorData;
import com.bbquantum.smartfarmingbackend.Entity.WeatherData;
import com.bbquantum.smartfarmingbackend.Repository.FieldsRepo;
import com.bbquantum.smartfarmingbackend.Repository.PreparedDataRepo;
import com.bbquantum.smartfarmingbackend.Repository.SensorDataRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DataService {

    private final SensorDataRepo sensorDataRepo;

    private final PreparedDataRepo proDataRepo;

    private final FieldsRepo fieldsRepo;

    public DataService(SensorDataRepo sensorDataRepo, PreparedDataRepo proDataRepo, FieldsRepo fieldsRepo) {
        this.sensorDataRepo = sensorDataRepo;
        this.proDataRepo = proDataRepo;
        this.fieldsRepo = fieldsRepo;
    }

    public void saveRawData(com.bbquantum.smartfarmingbackend.DTO.MQTT.SensorData sensorData) {
        Fields field = fieldsRepo.findByFieldId(sensorData.getFarmId()).orElse(null);
        if (field == null) {
            System.out.println("Invalid field Id");
            return;
        }

        //Save raw sensor Data
        try {
            SensorData data = new SensorData(
                    field,
                    sensorData.getSoilMoisture(),
                    sensorData.getSoilTemperature(),
                    sensorData.getHumidity(),
                    sensorData.getLightIntensity(),
                    LocalDateTime.now(),
                    false
            );

            sensorDataRepo.save(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedDataHolder prepareData(PreparedData data) {
        try {
            return new PreparedDataHolder(
                    (int) data.getPreparedDataId(),
                    data.getSoilMoisture(),
                    data.getSoilTemperature(),
                    data.getHumidity(),
                    data.getLightIntensity(),
                    data.getRainFallPossibility()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void processRawData(SensorData sensorData, WeatherData weatherData) {
        try {

            PreparedData storeData = new PreparedData(
                    sensorData.getSoilMoisture(),
                    sensorData.getTemperature(),
                    sensorData.getHumidity(),
                    sensorData.getLightIntensity(),
                    weatherData.getRainFallProbability(),
                    LocalDateTime.now(),
                    sensorData,
                    weatherData,
                    false
            );

            proDataRepo.save(storeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
