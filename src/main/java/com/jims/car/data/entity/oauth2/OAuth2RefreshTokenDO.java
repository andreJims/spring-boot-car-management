package com.jims.car.data.entity.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OAuth2RefreshTokenDO {

	@Id
    private String jti;

	@Column(columnDefinition="TEXT")
	private String refreshToken;

	private String username;

	public OAuth2RefreshTokenDO() {
		super();
	}

	public OAuth2RefreshTokenDO(String jti, String refreshToken, String username) {
		super();
		this.jti = jti;
		this.refreshToken = refreshToken;
		this.username = username;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
