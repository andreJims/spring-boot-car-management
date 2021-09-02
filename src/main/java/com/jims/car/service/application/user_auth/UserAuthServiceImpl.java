package com.jims.car.service.application.user_auth;

import com.jims.car.data.dto.UserAuthDTO;
import com.jims.car.constraint.factory.user_auth.UserAuthMapper;
import com.jims.car.service.dao.user_auth.UserAuthDAO;

import com.jims.car.service.repository.user.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDAO userAuthDAO;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthRepository userAuthRepository;

    private UserAuthDTO save(UserAuthDTO userAuthDTO) {
        userAuthDTO.setPassword(passwordEncoder.encode(userAuthDTO.getPassword()));
        return userAuthMapper.userAuthToUserAuthDTO(
                userAuthDAO.create(userAuthMapper.userAuthDTOtoUserAuth(userAuthDTO))
        );
    }

    @Override
    public void init() {

    }
}
