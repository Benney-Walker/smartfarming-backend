package com.bbquantum.smartfarmingbackend.Repository;

import com.bbquantum.smartfarmingbackend.Entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherDataRepo extends JpaRepository<WeatherData, Integer> {
    Optional<WeatherData> findByIsProcessed(boolean isProcessed);
}
