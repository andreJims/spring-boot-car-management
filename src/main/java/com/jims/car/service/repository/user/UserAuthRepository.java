package com.jims.car.service.repository.user;

import java.util.List;

import com.jims.car.data.entity.user_auth.UserAuth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    List<UserAuth> findByUsername(String userName);
}