package com.jims.car.constraint.factory.user_auth;

import java.util.List;

import com.jims.car.data.dto.UserAuthDTO;
import com.jims.car.data.entity.user_auth.UserAuth;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface UserAuthMapper {

	UserAuthDTO userAuthToUserAuthDTO(UserAuth userAuth);

    List<UserAuthDTO> userAuthListToUserAuthDTOList(List<UserAuth> userAuth);

    @InheritInverseConfiguration
    UserAuth userAuthDTOtoUserAuth(UserAuthDTO userAuthDto);
    
}
