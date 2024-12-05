package com.easybuy_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class EasybuyCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasybuyCartApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

// 	not currently using web client
//	@Bean
//	@LoadBalanced
//	public WebClient webClient() {
//		HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
//		return WebClient.builder()
//			.clientConnector(new ReactorClientHttpConnector(httpClient))
//			.build();
//	}

}
