package com.bbquantum.smartfarmingbackend.Repository;

import com.bbquantum.smartfarmingbackend.Entity.EntityIdGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityIdGenRepo extends JpaRepository<EntityIdGeneration, Integer> {

    Optional<EntityIdGeneration> findByEntityName(String entityName);
}
