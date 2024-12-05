package com.easybuy.user_details_service.security_config;

import com.easybuy.user_details_service.dto.AuthServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthServiceDetails authServiceDetails;
    @Autowired
    public SecurityConfig(AuthServiceDetails authServiceDetails) {
        this.authServiceDetails = authServiceDetails;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
           return httpSecurity.authorizeHttpRequests(
                   authorizationManagerRequestMatcherRegistry ->
                   authorizationManagerRequestMatcherRegistry
                            .anyRequest()
                            .permitAll())
//                            .requestMatchers(request -> isRequestFromAllowedHost(request))
//                            .permitAll()
//                            .anyRequest()
//                            .denyAll())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private boolean isRequestFromAllowedHost(HttpServletRequest request) {
//        // Allow only requests that come from "localhost" or "127.0.0.1" on the /auth path
//        String remoteHost = request.getRemoteHost();
//        String remoteServerPort = String.valueOf(request.getServerPort());
//        String remoteAddr = request.getRemoteAddr();
//        String requestURI = request.getRequestURI();
//        System.out.println("host: "+remoteHost+"Port: "+remoteServerPort+"Address: "+remoteAddr);
//        System.out.println("Authhost: "+authServiceDetails.getAuthServiceHost()+"AuthPort: "+authServiceDetails.getAuthServicePort()+"AuthAddress: "+authServiceDetails.getAuthServiceAddress());
//
//        boolean isLocalhost = (authServiceDetails.getAuthServiceHost().equals(remoteHost)
//                 || authServiceDetails.getAuthServiceAddress().equals(remoteAddr)
//                 || authServiceDetails.getAuthServiceAddress().equals(remoteAddr)
//                 || authServiceDetails.getAuthServiceAddress().equals(remoteAddr));
//        boolean isAuthPath = requestURI.startsWith("/easybuy-auth");
//        boolean isAuthPort = remoteServerPort.equals(authServiceDetails.getAuthServicePort());
//        return (isLocalhost && isAuthPath && isAuthPort) || ;
//    }
}
