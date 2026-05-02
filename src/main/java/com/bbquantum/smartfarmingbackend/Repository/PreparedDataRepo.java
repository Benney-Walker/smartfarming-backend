package com.bbquantum.smartfarmingbackend.Repository;

import com.bbquantum.smartfarmingbackend.Entity.PreparedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreparedDataRepo extends JpaRepository<PreparedData, Integer> {
    Optional<PreparedData> findByIsDecisionMade(boolean isDecisionMade);
}
