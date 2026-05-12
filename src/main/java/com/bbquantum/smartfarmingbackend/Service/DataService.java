package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.Components.Util;
import com.bbquantum.smartfarmingbackend.Contants.ActionStatus;
import com.bbquantum.smartfarmingbackend.Contants.Model;
import com.bbquantum.smartfarmingbackend.Contants.ModelDecision;
import com.bbquantum.smartfarmingbackend.DTO.HybridCom.InputResponse;
import com.bbquantum.smartfarmingbackend.DTO.HybridCom.PreparedDataHolder;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.ActionFeedBack;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.ReceivedSensorData;
import com.bbquantum.smartfarmingbackend.DTO.WeatherDTO.ReceivedForcast;
import com.bbquantum.smartfarmingbackend.Entity.*;
import com.bbquantum.smartfarmingbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DataService {

    @Autowired
    private SensorDataRepo sensorDataRepo;

    @Autowired
    private PreparedDataRepo preparedDataRepo;

    @Autowired
    private FieldsRepo fieldsRepo;

    @Autowired
    private ModelResponseRepo modelResponseRepo;

    @Autowired
    private IrrigationActionsRepo irrigationActionsRepo;

    @Autowired
    private WeatherDataRepo weatherDataRepo;

    @Autowired
    private Util util;

    public void saveSensorData(ReceivedSensorData receivedSensorData) {
        /*
        Fields field = fieldsRepo.findByFieldId(receivedSensorData.getFarmId()).orElse(null);
        if (field == null) {
            System.out.println("Invalid field Id");
            return;
        }*/
        System.out.println("soil moisture= " + receivedSensorData.getSoilMoisture());
        System.out.println("soil temperature= " + receivedSensorData.getSoilTemperature());
        System.out.println("humidity= " + receivedSensorData.getHumidity());
        System.out.println("Light intensity= " + receivedSensorData.getLightIntensity());

        //Save raw sensor Data
        try {
            SensorData data = new SensorData(
                    receivedSensorData.getSoilMoisture(),
                    receivedSensorData.getSoilTemperature(),
                    receivedSensorData.getHumidity(),
                    receivedSensorData.getLightIntensity(),
                    LocalDateTime.now(),
                    false
            );

            sensorDataRepo.save(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveReceivedForecast(ReceivedForcast data) {

        try {
            WeatherData newData = new WeatherData(
                    data.getLocation(),
                    data.getRainFallProbability(),
                    data.getTimeStamp()
            );

            weatherDataRepo.save(newData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save forecast data", e);
        }
    }

    //Prepares data to be sent to model
    public PreparedDataHolder prepareData(PreparedData data) {
        try {
            return new PreparedDataHolder(
                    data.getPreparedDataId(),
                    Double.parseDouble(data.getSoilMoisture()),
                    Double.parseDouble(data.getSoilTemperature()),
                    Double.parseDouble(data.getHumidity()),
                    Double.parseDouble(data.getLightIntensity()),
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
                    util.generateEntityId("PREPARED_DATA"),
                    sensorData.getSoilMoisture(),
                    sensorData.getTemperature(),
                    sensorData.getHumidity(),
                    sensorData.getLightIntensity(),
                    weatherData.getRainFallProbability(),
                    LocalDateTime.now(),
                    sensorData,
                    weatherData
            );

            preparedDataRepo.save(storeData);
            sensorData.setProcessed(true);
            sensorDataRepo.save(sensorData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Stores model response
    public ModelResponse storeModelResponse(InputResponse response, Model model) {
        PreparedData preparedData = preparedDataRepo.findByPreparedDataId(response.getPreparedDataId()).orElse(null);
        if (preparedData == null) return null;

        ModelResponse modelResponse = new ModelResponse(
                ModelDecision.valueOf(response.getDecision()),
                response.getWaterQuantity(),
                response.getConfidence(),
                response.getDecisionReason(),
                LocalDateTime.now(),
                model,
                preparedData
        );

        modelResponseRepo.save(modelResponse);

        return modelResponse;
    }

    public void updateActionDetails(ActionFeedBack feedBack) {
        try {
            IrrigationActions action = irrigationActionsRepo.findByActionId(feedBack.getActionId()).orElse(null);
            if (action == null) {
                System.out.println(feedBack.getActionId());
                return;
            }

            action.setActionStatus(ActionStatus.valueOf(feedBack.getActionStatus()));
            action.setFeedBackMessage(feedBack.getMessage());
            irrigationActionsRepo.save(action);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SensorData getUnprocessedSensorData() {
        return sensorDataRepo.findByIsProcessed(false).orElse(null);
    }

    public WeatherData getRespectiveHourlyForecast(LocalDateTime sensorDataTime) {

        LocalDateTime hourRange = sensorDataTime
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        return weatherDataRepo.findByTimeStamp(hourRange).orElse(null);
    }
}
