package com.bbquantum.smartfarmingbackend.Repository;

import com.bbquantum.smartfarmingbackend.Entity.IrrigationActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IrrigationActionsRepo extends JpaRepository<IrrigationActions, Integer> {
    Optional<IrrigationActions> findByActionId(String actionId);
}
