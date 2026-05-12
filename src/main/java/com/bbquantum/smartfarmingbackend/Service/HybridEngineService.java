package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.HybridCom.PreparedDataHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HybridEngineService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${hybrid.model.api.url}")
    private String modelApi;

    public Map getIrrigationDecision(PreparedDataHolder data) {

        Map<String, Object> request = Map.of(
                "soilMoisture", data.getSoilMoisture(),
                "temperature", data.getSoilTemperature(),
                "humidity", data.getHumidity(),
                "lightIntensity", data.getLightIntensity(),
                "rainProbability", data.getRainProbability()
        );

        return restTemplate.postForObject(
                modelApi,
                request,
                Map.class
        );
    }

    // Template
    public String checkModelStatus() {
        return "ONLINE";
    }
}
