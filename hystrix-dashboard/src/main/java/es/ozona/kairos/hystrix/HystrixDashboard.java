package es.ozona.kairos.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
// @EnableTurbine
@EnableDiscoveryClient
public class HystrixDashboard {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboard.class, args);
	}
}
