package com.bbquantum.smartfarmingbackend.Service;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class MqttService {

    private MqttClient client;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final String BROKER = "tcp://shuttle.proxy.rlwy.net:47897";
    private static final String SENSOR_TOPIC = "smartfarm/sensors";
    private static final String COMMAND_TOPIC = "smartfarm/commands";

    @PostConstruct
    public void init() {
        try {
            client = new MqttClient(BROKER, MqttClient.generateClientId());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            client.connect(options);

            System.out.println("Spring connected to broker");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
