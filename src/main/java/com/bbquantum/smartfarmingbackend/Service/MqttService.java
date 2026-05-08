package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.MQTT.ActionFeedBack;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.Alerts;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.ReceivedSensorData;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
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

    private MqttClient client;

    private static final String BROKER = "tcp://shuttle.proxy.rlwy.net:47897";
    private static final String SENSOR_TOPIC = "smartfarm/sensors";
    private static final String ALERT_TOPIC = "smartfarm/alert";
    private static final String FEEDBACK_TOPIC = "smartfarm/feedback";
    private static final String COMMAND_TOPIC = "smartfarm/commands";

    @PostConstruct
    public void init() {
        connectToBroker();
    }

    private void connectToBroker() {
        try {
            client = new MqttClient(BROKER, MqttClient.generateClientId(), new MemoryPersistence());

            // Set up the Callback BEFORE connecting
            client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    // This triggers on initial connect AND every automatic reconnect
                    System.out.println("✅ MQTT Connected! Reconnect status: " + reconnect);
                    subscribeToTopics();
                }

                @Override
                public void connectionLost(Throwable cause) {
                    System.err.println("❌ MQTT Connection lost: " + cause.getMessage());
                    // Automatic reconnect is enabled, so we just wait for connectComplete
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Received message on unhandled topic: " + topic);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true); // Paho handles the retry timing
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(60); // Helps detect connection loss

            client.connect(options);

        } catch (Exception e) {
            System.err.println("Initialization failed, will retry automatically: " + e.getMessage());
        }
    }

    private void subscribeToTopics() {
        try {
            client.subscribe(SENSOR_TOPIC, this::handleSensorData);
            client.subscribe(ALERT_TOPIC, this::handleAlert);
            client.subscribe(FEEDBACK_TOPIC, this::handleFeedback);
            System.out.println("📡 Subscribed to all topics");
        } catch (Exception e) {
            System.err.println("Failed to subscribe: " + e.getMessage());
        }
    }

    private void handleSensorData(String topic, MqttMessage message) {

        String payload = new String(message.getPayload());

        ReceivedSensorData receivedData = mapper.readValue(payload, ReceivedSensorData.class);

        dataService.saveSensorData(receivedData);
    }

    private void handleAlert(String topic, MqttMessage message) {

        String payload = new String(message.getPayload());

        Alerts alert = mapper.readValue(payload, Alerts.class);

        notificationService.sensorNotRespondingMessage(alert);
    }

    private void handleFeedback(String topic, MqttMessage message) {

        String payload = new String(message.getPayload());

        ActionFeedBack feedBack = mapper.readValue(payload, ActionFeedBack.class);

        dataService.updateActionDetails(feedBack);
    }

    //Sends irrigation commands to ESP32
    public void sendCommand(Object commandObj) {
        if (!client.isConnected()) {
            System.err.println("Cannot send command: MQTT Client not connected.");
            return;
        }
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
