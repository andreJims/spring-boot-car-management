package com.jims.car.config.security.authorization_server;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication != null) {
    		/*CustomUserDetails userDetails = (CustomUserDetails) userAuthentication.getPrincipal();

            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("xxxx",xxx);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);*/

        }
        return accessToken;
    }
}