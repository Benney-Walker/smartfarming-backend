package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.DTO.WebSocket.Action;
import com.bbquantum.smartfarmingbackend.DTO.WebSocket.ActivityMessage;
import com.bbquantum.smartfarmingbackend.DTO.WebSocket.AlertMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendSystemAlert(AlertMessage message) {
        messagingTemplate.convertAndSend(
                "/topic/admin/alert", message
        );
    }

    public void sendActivity(ActivityMessage message) {
        messagingTemplate.convertAndSend(
                "/topic/admin/activity", message
        );
    }

    public void sendIrrigationActions(Action action) {
        messagingTemplate.convertAndSend(
                "/topic/admin/action"
        );
    }

    public void sendHybridStatus(String status) {
        messagingTemplate.convertAndSend(
                "/topic/admin/hybrid-status", status
        );
    }
}
