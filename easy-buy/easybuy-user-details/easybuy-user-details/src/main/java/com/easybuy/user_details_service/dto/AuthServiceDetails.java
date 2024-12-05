package com.easybuy.user_details_service.dto;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceDetails {

    private final DiscoveryClient discoveryClient;
    private String authServiceHost = "172.17.208.1"; // Default host
    private String authServiceAddress = "172.17.208.1"; // Default address
    private String authServicePort = "9018"; // Default port

    @Autowired
    public AuthServiceDetails(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    @PostConstruct
    public void init() {
            try {
                // Fetch the first available instance of auth-service
                var instances = discoveryClient.getInstances("easybuy-auth-service");

                if (instances == null || instances.isEmpty()) {
                    throw new RuntimeException("auth-service not found in service registry");
                }

                // Get the first instance and populate the fields
                var authServiceInstance = instances.get(0);
                this.authServiceHost = authServiceInstance.getHost();
                this.authServiceAddress = authServiceInstance.getUri().toString();
                this.authServicePort = String.valueOf(authServiceInstance.getPort());

            } catch (Exception e) {
                throw new RuntimeException("Error initializing AuthServiceDetails", e);
            }
        }
    public String getAuthServiceHost() {
        return authServiceHost;
    }

    public String getAuthServiceAddress() {
        return authServiceAddress;
    }

    public String getAuthServicePort() {
        return authServicePort;
    }
}
