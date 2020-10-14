package es.ozona.security.oauth2.server.outboundservices;

import java.util.Map;

public interface ExternalUserAditionalInfoService {
	Map<String, Object> getUserAditionalInfo(String username);
}
