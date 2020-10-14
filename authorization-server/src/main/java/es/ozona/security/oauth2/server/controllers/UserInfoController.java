package es.ozona.security.oauth2.server.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ozona.security.oauth2.server.outboundservices.ExternalUserAditionalInfoService;

@RestController
public class UserInfoController {

	@Autowired
	protected ExternalUserAditionalInfoService externalUserAditionalInfoService;
	
	@GetMapping(value = "/userinfo")
	public Map<String, Object> user(Principal principal) {

		Map<String, Object> userInfoMap = externalUserAditionalInfoService.getUserAditionalInfo(principal.getName());
		userInfoMap.put("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		userInfoMap.put("username", principal.getName());
		
		return userInfoMap;

	}

}
