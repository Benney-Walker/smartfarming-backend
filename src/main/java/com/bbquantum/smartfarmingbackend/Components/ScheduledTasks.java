package com.bbquantum.smartfarmingbackend.Components;

import com.bbquantum.smartfarmingbackend.Contants.Model;
import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.InputResponse;
import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.PreparedDataHolder;
import com.bbquantum.smartfarmingbackend.Entity.ModelResponse;
import com.bbquantum.smartfarmingbackend.Entity.PreparedData;
import com.bbquantum.smartfarmingbackend.Entity.SensorData;
import com.bbquantum.smartfarmingbackend.Entity.WeatherData;
import com.bbquantum.smartfarmingbackend.Repository.PreparedDataRepo;
import com.bbquantum.smartfarmingbackend.Repository.SensorDataRepo;
import com.bbquantum.smartfarmingbackend.Repository.WeatherDataRepo;
import com.bbquantum.smartfarmingbackend.Service.DataService;
import com.bbquantum.smartfarmingbackend.Service.DecisionAndActionService;
import com.bbquantum.smartfarmingbackend.Service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private DataService dataService;

    @Autowired
    private SensorDataRepo sensorDataRepo;

    @Autowired
    private WeatherDataRepo weatherDataRepo;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private Util util;

    @Autowired
    private DecisionAndActionService DAService;

    @Autowired
    private PreparedDataRepo preparedDataRepo;

    @Scheduled(fixedRate = 180000) //Runs every 3 minutes
    public void prepareReceivedData() {
        // Unprocessed sensor data
        SensorData sensorRecord = sensorDataRepo.findByIsProcessed(false).orElse(null);

        // Unprocessed weather data
        WeatherData weatherData = weatherDataRepo.findByIsProcessed(false).orElse(null);

        if (sensorRecord == null || weatherData == null) {
            return;
        }

        dataService.processRawData(sensorRecord, weatherData);

        sensorRecord.setProcessed(true);
        sensorDataRepo.save(sensorRecord);

        weatherData.setProcessed(true);
        weatherDataRepo.save(weatherData);
    }

    @Scheduled(fixedRate = 300000) //Checks model status every 5 minutes
    public void checkHybridStatus() {
        webSocketService.sendHybridStatus(util.checkModelStatus());
    }

    @Scheduled(fixedRate = 240000) // sends data to model for prediction and decision every 4 minutes
    public void askForIrrigationDecisions() {
        PreparedData data = preparedDataRepo.findByIsDecisionMade(false).orElse(null);
        if (data == null) return;

        PreparedDataHolder dataHolder = new PreparedDataHolder(
                data.getPreparedDataId(),
                Double.parseDouble(data.getSoilMoisture()),
                Double.parseDouble(data.getSoilTemperature()),
                Double.parseDouble(data.getHumidity()),
                Double.parseDouble(data.getLightIntensity()),
                Double.parseDouble(data.getRainFallPossibility())
        );

        String cloudModelStatus = util.checkModelStatus();

        InputResponse modelResponse;
        if (cloudModelStatus.equals("OFFLINE")) {
            modelResponse = DAService.callEdgeModel(dataHolder);

        } else {
            modelResponse = DAService.callCloudModel(dataHolder);

            ModelResponse storedModelResponse = dataService.storeModelResponse(modelResponse, Model.CLOUD_MODEL);

        }
    }
}
