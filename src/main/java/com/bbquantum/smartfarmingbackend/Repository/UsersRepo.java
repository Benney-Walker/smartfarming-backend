package com.bbquantum.smartfarmingbackend.Repository;

import com.bbquantum.smartfarmingbackend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmailAddress(String email);

    Optional<Users> findByUserName(String username);
}
