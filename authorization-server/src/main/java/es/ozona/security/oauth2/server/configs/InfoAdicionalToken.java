package es.ozona.security.oauth2.server.configs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

//	@Autowired
//	private UserService usuarioService;

	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();

		// User usuario = usuarioService.findByUsername(authentication.getName());
		info.put("name", getAttribute("cn"));
		info.put("surename", getAttribute("sn"));
		info.put("mail", getAttribute("mail"));
		info.put("office", getAttribute("physicalDeliveryOfficeName"));
		info.put("phone", getAttribute("mobile"));
		info.put("employeeNumber", getAttribute("employeeNumber"));
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

	private String getAttribute(String attribute) {
		final List<String> attributeValues = ldapTemplate.search(
				LdapQueryBuilder.query().where("objectclass").is("person").and("uid").is("jeijo"),
				new AttributesMapper<String>() {
					public String mapFromAttributes(Attributes attrs)
							throws NamingException, javax.naming.NamingException {
						return attrs.get(attribute).get().toString();
					}
				});

		return attributeValues.size() == 0 ? null : attributeValues.get(0);
	}

}
