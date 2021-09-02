package com.jims.car.config.security.authorization_server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

import com.jims.car.data.entity.oauth2.OAuth2AccessTokenDO;
import com.jims.car.data.entity.oauth2.OAuth2RefreshTokenDO;
import com.jims.car.service.dao.user_auth.UserAuthDAO;
import com.jims.car.service.repository.oauth2.OAuth2AccessTokenDOJpaRepository;
import com.jims.car.service.repository.oauth2.OAuth2RefreshTokenDOJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


public class PostgresJwtTokenStore extends JwtTokenStore {

    @Autowired
    private OAuth2AccessTokenDOJpaRepository oAuth2AccessTokenDOJpaRepository;

    @Autowired
    private OAuth2RefreshTokenDOJpaRepository oAuth2RefreshTokenDOJpaRepository;

    @Autowired
    private UserAuthDAO userAuthDAO;

    public static final String TOKEN_ID = AccessTokenConverter.JTI;

    public PostgresJwtTokenStore(final JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public void storeAccessToken(final OAuth2AccessToken token, final OAuth2Authentication authentication) {

        if (token != null && token.getValue() != null) {
            String accessToken = token.getValue();
            String refreshToken = token.getRefreshToken().getValue();
            String jti = token.getAdditionalInformation().get(TOKEN_ID).toString();
            OAuth2AccessTokenDO auth2AccessTokenDO = null;

            if (authentication.getUserAuthentication() != null) {

                UserDetails user = (UserDetails) authentication.getUserAuthentication().getPrincipal();
                String userName = user.getUsername();

                auth2AccessTokenDO = new OAuth2AccessTokenDO(jti, accessToken, userName);
                OAuth2RefreshTokenDO auth2RefreshTokenDO = new OAuth2RefreshTokenDO(jti, refreshToken, userName);

                oAuth2AccessTokenDOJpaRepository.save(auth2AccessTokenDO);
                oAuth2RefreshTokenDOJpaRepository.save(auth2RefreshTokenDO);
            } else {
                auth2AccessTokenDO = new OAuth2AccessTokenDO(jti, accessToken, null);
                oAuth2AccessTokenDOJpaRepository.save(auth2AccessTokenDO);
            }

        }

    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {

        OAuth2AccessToken accessToken = super.readAccessToken(tokenValue);
        if (accessToken.isExpired()) {
            return accessToken;
        }
        return null;
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {

        OAuth2RefreshTokenDO auth2RefreshTokenDO = oAuth2RefreshTokenDOJpaRepository.findOneByRefreshToken(tokenValue);
        if (auth2RefreshTokenDO != null) {
            return super.readRefreshToken(tokenValue);
        }
        return null;
    }

    @Override
    public OAuth2Authentication readAuthentication(final OAuth2AccessToken token) {
        OAuth2Authentication oAuth2Authentication = null;
        OAuth2Request oAuth2Request = null;
        Boolean isAuthenticated = true;

        @SuppressWarnings("unchecked")
        List<String> grantedAuthorities = (List<String>) token.getAdditionalInformation().get("authorities");
        HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if (grantedAuthorities != null) {
            for (String grantedAuthority : grantedAuthorities) {
                authorities.add(new SimpleGrantedAuthority(grantedAuthority));
            }
        } else authorities.add(new SimpleGrantedAuthority("DEFAULT"));
        String userPrinsipal = (String) token.getAdditionalInformation().get("user_name");
        if (userPrinsipal == null) userPrinsipal = "defaultUser";

        oAuth2Request = new OAuth2Request(null, userPrinsipal, authorities, true, null, null, null, null, null);

        User principal = new User(userPrinsipal, "", authorities);
        List<GrantedAuthority> listGrantedAuthority = new ArrayList<GrantedAuthority>(authorities);
        TestingAuthenticationToken userAuthentication = new TestingAuthenticationToken(principal, null, listGrantedAuthority);
        userAuthentication.setAuthenticated(isAuthenticated);

        oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);

        return oAuth2Authentication;
    }


}
