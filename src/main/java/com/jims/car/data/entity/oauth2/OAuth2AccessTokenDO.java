package com.jims.car.data.entity.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OAuth2AccessTokenDO {

	@Id
    private String jti;

	@Column(columnDefinition="TEXT")
	private String accessToken;

	private String username;

	public OAuth2AccessTokenDO() {
		super();
	}

	public OAuth2AccessTokenDO(String jti, String accessToken, String username) {
		super();
		this.jti = jti;
		this.accessToken = accessToken;
		this.username = username;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
