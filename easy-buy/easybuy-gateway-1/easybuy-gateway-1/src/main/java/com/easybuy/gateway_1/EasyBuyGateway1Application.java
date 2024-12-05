package com.easybuy.gateway_1;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class EasyBuyGateway1Application {
      public static void main(String[] args) {
            SpringApplication.run(EasyBuyGateway1Application.class, args);

      }
//      @Bean
//      public GlobalFilter globalFilter() {
//            return (exchange, chain) -> {
//                  System.out.println("First Global filter");
//                  return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                        System.out.println("Second Global filter");
//                  }));
//            };
//      }
      @Bean
      @Primary
      @LoadBalanced
      public WebClient webClient() {
            HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
            return WebClient.builder()
                     .clientConnector(new ReactorClientHttpConnector(httpClient))
                     .build();
      }
      @Bean
      public HttpClient httpClient() {
            return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
      }
//      @Bean
//      @LoadBalanced
//      public WebClient.Builder webclientBuilder() {
//            return WebClient.builder();
//      }
//      @Bean
//      @LoadBalanced
//      public RestTemplate restTemplate() {
//            return new RestTemplate();
//      }
//      @Bean
//      public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//            return builder.routes()
//                     .route("easybuy-cart", r -> r.path("/easybuy/cart/**")
//                              .uri("http://localhost:6006"))
//                     .build();
//      }

}
