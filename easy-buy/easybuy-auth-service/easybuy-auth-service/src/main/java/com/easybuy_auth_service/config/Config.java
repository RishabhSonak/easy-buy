package com.easybuy_auth_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;



@Configuration
public class Config {
      @Bean
      @LoadBalanced
      public RestTemplate restTemplate() {
            return new RestTemplate(new SimpleClientHttpRequestFactory());
      }
      @Bean
      public ObjectMapper objectMapper(){
            return new ObjectMapper();
      }
      @Bean
      @LoadBalanced
      public WebClient loadBalancedWebClientBuilder() {
            return WebClient.builder().build();
      }


}
