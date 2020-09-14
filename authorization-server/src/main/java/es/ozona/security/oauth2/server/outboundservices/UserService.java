package es.ozona.security.oauth2.server.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.User;

//@FeignClient(name = "identity-service", fallback = UserServiceFallback.class)
public interface UserService {

	User findByUsername(String username);

}
