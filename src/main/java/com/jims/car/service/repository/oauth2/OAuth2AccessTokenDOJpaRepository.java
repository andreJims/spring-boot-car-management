package com.jims.car.service.repository.oauth2;

import java.util.List;

import com.jims.car.data.entity.oauth2.OAuth2AccessTokenDO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2AccessTokenDOJpaRepository extends JpaRepository<OAuth2AccessTokenDO,String> {
	
	List <OAuth2AccessTokenDO> deleteByUsername(String userName);
	OAuth2AccessTokenDO findOneByAccessToken(String accessToken);
	
}
