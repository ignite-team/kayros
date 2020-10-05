package es.ozona.security.oauth2.server.infrastructure.ldap;

import java.util.List;
import java.util.Map;

public interface LdapUserInfoService {

	public String getAttribute(String username, String attribute);

	public Map<String, Object> getAttributes(String username, List<String> attribute);
	
}
