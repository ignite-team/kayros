package es.ozona.security.oauth2.server.outboundservices;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.security.oauth2.server.infrastructure.ldap.LdapUserInfoService;

@Service
public class ExternalUserAditionalInfoServiceImpl implements ExternalUserAditionalInfoService {

	private static final Logger LOG = LoggerFactory.getLogger(ExternalUserAditionalInfoServiceImpl.class);

	@Autowired
	protected LdapUserInfoService userAditionalInfoService;

	protected List<String> attributes = Arrays.<String>asList(
			new String[] { "cn", "sn", "mail", "physicalDeliveryOfficeName", "mobile", "employeeNumber" });

	@Override
	public Map<String, Object> getUserAditionalInfo(String username) {
		return userAditionalInfoService.getAttributes(username, attributes);
	}
}
