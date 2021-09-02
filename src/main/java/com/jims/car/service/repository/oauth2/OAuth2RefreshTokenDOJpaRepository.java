package com.jims.car.service.repository.oauth2;

import java.util.List;

import com.jims.car.data.entity.oauth2.OAuth2RefreshTokenDO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2RefreshTokenDOJpaRepository extends JpaRepository<OAuth2RefreshTokenDO,String> {
	
	List <OAuth2RefreshTokenDO> deleteByUsername(String userName);
	OAuth2RefreshTokenDO findOneByRefreshToken(String accessToken);

}
