package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.Contants.ActionStatus;
import com.bbquantum.smartfarmingbackend.Contants.ModelDecision;
import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.InputResponse;
import com.bbquantum.smartfarmingbackend.DTO.MQTT.ActionFeedBack;
import com.bbquantum.smartfarmingbackend.DTO.HybridComDTO.PreparedDataHolder;
import com.bbquantum.smartfarmingbackend.Entity.IrrigationActions;
import com.bbquantum.smartfarmingbackend.Repository.IrrigationActionsRepo;
import org.springframework.stereotype.Service;

@Service
public class DecisionAndActionService {

    private final IrrigationActionsRepo irrigationActionsRepo;

    private final WebSocketService webSocketService;

    public DecisionAndActionService(IrrigationActionsRepo irrigationActionsRepo, WebSocketService webSocketService) {
        this.irrigationActionsRepo = irrigationActionsRepo;
        this.webSocketService = webSocketService;
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
    }

    public InputResponse callCloudModel(PreparedDataHolder data) {

        return null;
    }

    public InputResponse callEdgeModel(PreparedDataHolder data) {

        return null;
    }

    private boolean isIrrigationNeeded(String decision) {
        return !decision.equals(ModelDecision.NO_IRRIGATION.name());
    }

    public void storeModelResponse(InputResponse response) {

    }
}
