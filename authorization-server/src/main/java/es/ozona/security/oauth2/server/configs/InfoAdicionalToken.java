package es.ozona.security.oauth2.server.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import es.ozona.security.oauth2.server.outboundservices.ExternalUserAditionalInfoService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private ExternalUserAditionalInfoService userAditionalInfoService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(userAditionalInfoService.getUserAditionalInfo(authentication.getName()));

		return accessToken;
	}

}
