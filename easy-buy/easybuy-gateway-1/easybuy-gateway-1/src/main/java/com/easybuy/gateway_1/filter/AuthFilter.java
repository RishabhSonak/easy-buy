package com.easybuy.gateway_1.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

//    @Override
//    public GatewayFilter apply(Config config) {
//        //Custom Pre Filter. Suppose we can extract JWT and perform Authentication
//        return (exchange, chain) -> {
//            System.out.println("First pre filter" + exchange.getRequest().getPath());
//            //Custom Post Filter.Suppose we can call error response handler based on error code.
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                System.out.println("First post filter");
//            }));
//        };
//    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            logger.info("Intercepted request path: {}", path);
            logger.debug("Intercepted request path: {}", path);

            // allow all requests to /auth paths without validation
            if (path.startsWith("/auth")) {
                logger.info("Request to /auth path allowed without validation");
                return chain.filter(exchange);
            }

            // for other paths, validate the token
            String authToken = extractToken(exchange);
            if (authToken == null) {
                logger.warn("Authorization header missing or invalid for path: {}", path);
                return onError(exchange, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
            }

            logger.info("Validating token for path: {}", path);
            return validateToken(authToken, exchange, chain);
        };
    }

    private Mono<Void> validateToken(String authToken, ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Token validation initiated");
        String validationUri="http://localhost:9018/auth/validate";
        return webClientBuilder.build()
                 .get()
                 .uri(validationUri)
                 .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                 .retrieve()
                 .onStatus(HttpStatusCode::isError, clientResponse -> {
                     if (clientResponse.statusCode() == HttpStatus.UNAUTHORIZED) {
                         logger.warn("Token is invalid or expired");
                         return Mono.error(new RuntimeException("Unauthorized: Token is invalid or expired."));
                     }
                     return clientResponse.createException();
                 })
                 .bodyToMono(String.class)
                 .flatMap(response -> {
                     logger.info("Token validated successfully");
                     return chain.filter(exchange);
                 })
                 .onErrorResume(ex -> {
                     logger.error("Error during token validation: {}", ex.getMessage());
                     return onError(exchange, ex.getMessage(), HttpStatus.UNAUTHORIZED);
                 });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse()
                 .writeWith(Mono.just(exchange.getResponse()
                          .bufferFactory()
                          .wrap(bytes)))
                 .doOnSubscribe(sub -> {
                     exchange.getResponse().setStatusCode(status);
                     exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
                 });
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        return authHeader != null && authHeader.startsWith("Bearer ") ? authHeader.substring(7) : null;
    }

    public static class Config {
        // Placeholder for future configuration
    }
}
