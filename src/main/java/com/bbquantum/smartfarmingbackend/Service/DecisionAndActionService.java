package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.Components.Util;
import com.bbquantum.smartfarmingbackend.Contants.ActionStatus;
import com.bbquantum.smartfarmingbackend.Contants.ModelDecision;
import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.InputResponse;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.ActionFeedBack;
import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.PreparedDataHolder;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.IrrigationAction;
import com.bbquantum.smartfarmingbackend.Entity.IrrigationActions;
import com.bbquantum.smartfarmingbackend.Entity.ModelResponse;
import com.bbquantum.smartfarmingbackend.Repository.IrrigationActionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class DecisionAndActionService {

    @Autowired
    private IrrigationActionsRepo irrigationActionsRepo;

    @Autowired
    private HybridEngineService hybridEngineService;

    @Autowired
    private MqttService mqttService;

    @Autowired
    private Util util;

    public InputResponse callCloudModel(PreparedDataHolder data) {

        Map response = hybridEngineService.getIrrigationDecision(data);

        String decision = (String) response.get("decision");
        int waterQuantity = (int) response.get("waterQuantity");
        int confidence = (int) response.get("confidence");
        String decisionReason = (String) response.get("decisionReason");
        String preparedDataId = (String) response.get("preparedDataId");

        return new InputResponse(
                decision,
                waterQuantity,
                confidence,
                decisionReason,
                preparedDataId
        );
    }

    public void updateActionDetails(ActionFeedBack feedBack) {
        try {
            IrrigationActions action = irrigationActionsRepo.findByActionId(feedBack.getActionId()).orElse(null);
            if (action == null) {
                System.out.println(feedBack.getActionId());
                return;
            }

            action.setActionStatus(ActionStatus.valueOf(feedBack.getActionStatus()));
            action.setMessage(feedBack.getMessage());
            irrigationActionsRepo.save(action);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public InputResponse callEdgeModel(PreparedDataHolder data) {

        return null;
    }

    private boolean isIrrigationNeeded(String decision) {
        return !decision.equals(ModelDecision.NO_IRRIGATION.name());
    }
}
