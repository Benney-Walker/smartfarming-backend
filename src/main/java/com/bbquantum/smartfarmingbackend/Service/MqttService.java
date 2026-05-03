package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.MQTT.ActionFeedBack;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.Alerts;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.SensorData;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class MqttService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private DataService dataService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private DecisionAndActionService dAService;

    private MqttClient client;

    private static final String BROKER = "tcp://shuttle.proxy.rlwy.net:47897";
    private static final String SENSOR_TOPIC = "smartfarm/sensors";
    private static final String ALERT_TOPIC = "smartfarm/alert";
    private static final String FEEDBACK_TOPIC = "smartfarm/feedback";
    private static final String COMMAND_TOPIC = "smartfarm/commands";

    @PostConstruct
    public void init() {
        try {
            client = new MqttClient(
                    BROKER, MqttClient.generateClientId(), new MemoryPersistence()
            );

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            client.connect(options);

            System.out.println("Spring connected to broker");

            //Receives sensor data
            client.subscribe(SENSOR_TOPIC, this::handleSensorData);

            //Receives alerts
            client.subscribe(ALERT_TOPIC, this::handleAlert);

            //Receives irrigation action feedback
            client.subscribe(FEEDBACK_TOPIC, this::handleFeedback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSensorData(String topic, MqttMessage message) {

        String payload = new String(message.getPayload());

        SensorData receivedData = mapper.readValue(payload, SensorData.class);

        dataService.saveRawData(receivedData);
    }

    private void handleAlert(String topic, MqttMessage message) {

        String payload = new String(message.getPayload());

        Alerts alert = mapper.readValue(payload, Alerts.class);

        notificationService.sensorNotRespondingMessage(alert);
    }

    private void handleFeedback(String topic, MqttMessage message) {

        String payload = new String(message.getPayload());

        ActionFeedBack feedBack = mapper.readValue(payload, ActionFeedBack.class);

        dAService.updateActionDetails(feedBack);
    }

    //Sends irrigation commands to ESP32
    public void sendCommand(Object commandObj) {
        try {
            String json = mapper.writeValueAsString(commandObj);

            MqttMessage message = new MqttMessage(json.getBytes());
            message.setQos(1);

            client.publish(COMMAND_TOPIC, message);

            System.out.println("🚀 Command sent: " + json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
