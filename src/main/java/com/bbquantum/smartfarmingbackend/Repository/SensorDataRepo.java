package com.bbquantum.smartfarmingbackend.Repository;

import com.bbquantum.smartfarmingbackend.Entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorDataRepo extends JpaRepository<SensorData, Integer> {
    Optional<SensorData> findByIsProcessed(boolean isProcessed);
}
