package es.ozona.security.oauth2.server.infrastructure.ldap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class LdapUserInfoServiceImpl implements LdapUserInfoService {

	@Autowired
	protected LdapTemplate ldapTemplate;

	@Override
	public String getAttribute(String username, String attribute) {
		Object value = internalGetAttributes(username, Arrays.asList(new String[] { attribute })).getOrDefault(attribute, null);;
		return value == null? null: value.toString();
	}

	@Override
	public Map<String, Object> getAttributes(String username, List<String> attributes) {
		return internalGetAttributes(username, attributes);
	}

	private Map<String, Object> internalGetAttributes(String username, List<String> attributes) {

		List<UserProperties> list = ldapTemplate.search(
				LdapQueryBuilder.query().where("objectclass").is("person").and("uid").is(username),
				new AttributesMapper<UserProperties>() {
					public UserProperties mapFromAttributes(Attributes attrs)
							throws NamingException, javax.naming.NamingException {

						UserProperties properties = new UserProperties();

						if (attrs == null || attrs.size() == 0) {
							return null;
						}

						for (String attribute : attributes) {
							properties.put(attribute, attrs.get(attribute).get().toString());
						}

						return properties;
					}
				});

		return list.size() > 0 ? list.get(0) : new HashMap<String, Object>();
	}

	private static class UserProperties extends HashMap<String, Object> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UserProperties() {
			super(0);
		}

	}

}
