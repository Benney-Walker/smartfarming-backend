package com.bbquantum.smartfarmingbackend.Components;

import com.bbquantum.smartfarmingbackend.Contants.Model;
import com.bbquantum.smartfarmingbackend.DTO.HybridCom.InputResponse;
import com.bbquantum.smartfarmingbackend.DTO.HybridCom.PreparedDataHolder;
import com.bbquantum.smartfarmingbackend.DTO.WeatherDTO.ReceivedForcast;
import com.bbquantum.smartfarmingbackend.Entity.ModelResponse;
import com.bbquantum.smartfarmingbackend.Entity.PreparedData;
import com.bbquantum.smartfarmingbackend.Entity.SensorData;
import com.bbquantum.smartfarmingbackend.Entity.WeatherData;
import com.bbquantum.smartfarmingbackend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private DataService dataService;

    @Autowired
    private Util util;

    @Autowired
    private DecisionAndActionService DAService;

    @Autowired
    private HybridEngineService engineService;

    @Autowired
    private WeatherService weatherService;

    @Scheduled(fixedRate = 60000) //Runs every 1 minutes
    public void prepareReceivedData() {

        // Unprocessed sensor data
        SensorData sensorRecord = dataService.getUnprocessedSensorData();
        if (sensorRecord == null) {
            return;
        }

        // Unprocessed weather data
        WeatherData weatherData = dataService.getRespectiveHourlyForecast(sensorRecord.getTimeOfArrival());
        if (weatherData == null) {
            return;
        }

        dataService.processRawData(sensorRecord, weatherData);
    }

    @Scheduled(fixedRate = 240000) // sends data to model for prediction and decision every 4 minutes
    public void askForIrrigationDecisions() {
        PreparedData data = null;
        if (data == null) return;

        PreparedDataHolder dataHolder = dataService.prepareData(data);

        String cloudModelStatus = engineService.checkModelStatus();

        InputResponse modelResponse;
        if (cloudModelStatus.equals("OFFLINE")) {
            modelResponse = DAService.callEdgeModel(dataHolder);

        } else {
            modelResponse = DAService.callCloudModel(dataHolder);

            ModelResponse storedModelResponse = dataService.storeModelResponse(modelResponse, Model.CLOUD_MODEL);

            DAService.sendIrrigationCommands(modelResponse, storedModelResponse);
        }
    }

    @Scheduled(fixedRate = 7200000) //Starts every 2(7200000) hours
    public void askForForecastData() {
        System.out.println("Started");
        List<ReceivedForcast> foreCastData = weatherService.getNextHoursForecast(2);

        for (ReceivedForcast forecast : foreCastData) {
            System.out.println("Location= " + forecast.getLocation());
            System.out.println("Rain probability= " + forecast.getRainFallProbability());
            System.out.println("Time stamp= " + forecast.getTimeStamp());

            dataService.saveReceivedForecast(forecast);
        }
    }
}
