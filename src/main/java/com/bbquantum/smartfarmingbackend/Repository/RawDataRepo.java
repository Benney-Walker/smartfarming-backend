package com.bbquantum.smartfarmingbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataRepo extends JpaRepository<RawDataRepo, Integer> {
}
