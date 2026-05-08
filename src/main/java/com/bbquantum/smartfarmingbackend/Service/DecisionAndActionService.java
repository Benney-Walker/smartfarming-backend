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

    public InputResponse callEdgeModel(PreparedDataHolder data) {

        return null;
    }

    public void sendIrrigationCommands(InputResponse response, ModelResponse modelResponse) {
        if (!isIrrigationNeeded(response.getDecision())) return;

        String actionId = util.generateEntityId("IRRIGATION_ACTION");

        try {

            IrrigationActions storeActions = new IrrigationActions(
                    actionId,
                    response.getWaterQuantity(),
                    LocalDateTime.now(),
                    ActionStatus.PENDING,
                    modelResponse
            );

            irrigationActionsRepo.save(storeActions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        IrrigationAction command = new IrrigationAction(
                actionId,
                response.getWaterQuantity()
        );

        mqttService.sendCommand(command);
    }

    private boolean isIrrigationNeeded(String decision) {
        return !decision.equals(ModelDecision.NO_IRRIGATION.name());
    }
}
