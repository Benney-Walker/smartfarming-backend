package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.PreparedDataHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HybridEngineService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String PYTHON_API_URL = "http://localhost:8000/predict";

    public Map getIrrigationDecision(PreparedDataHolder data) {

        Map<String, Object> request = Map.of(
                "soilMoisture", data.getSoilMoisture(),
                "temperature", data.getSoilTemperature(),
                "humidity", data.getHumidity(),
                "lightIntensity", data.getLightIntensity(),
                "rainProbability", data.getRainProbability()
        );

        return restTemplate.postForObject(
                PYTHON_API_URL,
                request,
                Map.class
        );
    }
}
