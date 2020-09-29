package es.ozona.micro.core.infrastructure.respository;

import java.util.UUID;

public interface UUIDIdentityGenerator {
	default String nextId() {
		return UUID.randomUUID().toString().toUpperCase();
	}
}
