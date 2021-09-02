package com.jims.car.service.dao.user_auth;

import java.util.Optional;

import com.jims.car.data.entity.user_auth.UserAuth;

public interface UserAuthDAO {

    UserAuth create(UserAuth newUserAuth);

    Optional<UserAuth> findByUsername(String username);
}
