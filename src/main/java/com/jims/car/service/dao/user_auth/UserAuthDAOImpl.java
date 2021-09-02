package com.jims.car.service.dao.user_auth;

import java.util.Optional;

import com.jims.car.service.repository.user.UserAuthRepository;
import com.jims.car.data.entity.user_auth.UserAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDAOImpl implements UserAuthDAO {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserAuth create(UserAuth newUserAuth) {
        return userAuthRepository.save(newUserAuth);
    }

    @Override
    public Optional<UserAuth> findByUsername(String username) {
        return userAuthRepository.findByUsername(username).stream().findFirst();
    }

}
