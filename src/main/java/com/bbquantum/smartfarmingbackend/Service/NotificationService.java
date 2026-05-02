package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.MQTT.Alerts;
import com.bbquantum.smartfarmingbackend.DTO.WebSocket.AlertMessage;
import com.bbquantum.smartfarmingbackend.Entity.Fields;
import com.bbquantum.smartfarmingbackend.Repository.FieldsRepo;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final FieldsRepo fieldsRepo;

    private final WebSocketService webSocketService;

    public NotificationService(FieldsRepo fieldsRepo, WebSocketService webSocketService) {
        this.fieldsRepo = fieldsRepo;
        this.webSocketService = webSocketService;
    }

    public void sensorNotRespondingMessage(Alerts alert) {
        Fields field = fieldsRepo.findByFieldId(alert.getFieldId()).orElse(null);
        if (field == null) {
            System.out.println("Invalid field Id");
            return;
        }

        AlertMessage alertMessage = new AlertMessage(
                alert.getTimeStamp(),
                field.getFieldName(),
                alert.getMessage()
        );

        webSocketService.sendSystemAlert(alertMessage);
    }
}
