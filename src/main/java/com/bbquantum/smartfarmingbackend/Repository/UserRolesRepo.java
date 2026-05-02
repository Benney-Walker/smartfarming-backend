package com.bbquantum.smartfarmingbackend.Repository;

import com.bbquantum.smartfarmingbackend.Entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepo extends JpaRepository<UserRoles, Integer> {
}
