package com.jims.car.config.security.user_details;

import java.util.ArrayList;
import java.util.Collections;

import com.jims.car.constraint.errors.ErrorsEnum;
import com.jims.car.constraint.errors.ObjectNotFoundException;
import com.jims.car.data.entity.user_auth.UserAuth;
import com.jims.car.service.dao.user_auth.UserAuthDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    private UserAuthDAO userAuthDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthDAO.findByUsername(s).orElseThrow(() -> new ObjectNotFoundException(ErrorsEnum.ERR_API_USER_IS_NOT_FOUND));

        if (userAuth == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        return new CustomUserDetails(userAuth.getUsername(), userAuth.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(userAuth.getRole())));

    }
}
