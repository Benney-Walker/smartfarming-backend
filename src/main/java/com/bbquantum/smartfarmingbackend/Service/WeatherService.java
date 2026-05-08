package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.WeatherDTO.ReceivedForcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.baseUrl}")
    private String baseUrl;

    public List<ReceivedForcast> getNextHoursForecast(int hoursAhead) {

        List<ReceivedForcast> formattedData = new ArrayList<>();

        try {
            String url = baseUrl + "?key=" + apiKey + "&q=Accra&days=1";

            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            String location = root.path("location").path("name").asText();

            JsonNode hours = root.path("forecast")
                    .path("forecastday")
                    .get(0)
                    .path("hour");

            //Gets current hour
            int currentHour = LocalDateTime.now().getHour();

            for (int i = 0; i < hoursAhead; i++) {

                int index = currentHour + i;

                if (index >= hours.size()) break;

                JsonNode hourNode = hours.get(index);

                int rain = hourNode.path("chance_of_rain").asInt();

                String timeString = hourNode.path("time").asText();

                LocalDateTime time = LocalDateTime.parse(
                        timeString,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                );

                formattedData.add(new ReceivedForcast(location, rain, time));
            }
        } catch (Exception e) {
            throw new RuntimeException("Weather fetch failed", e);
        }

        return formattedData;
    }
}
