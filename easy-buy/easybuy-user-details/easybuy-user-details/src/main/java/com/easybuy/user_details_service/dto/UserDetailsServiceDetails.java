//package com.easybuy.user_details_service.dto;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.stereotype.Component;
//import java.util.List;
//
//@Component
//public class UserDetailsServiceDetails {
//
//    private DiscoveryClient discoveryClient;
//    private String authServiceHost = "172.17.208"; // Default host
//    private String authServiceAddress = "http://172.17.208.1"; // Default address
//    private String authServicePort = "9045"; // Default port
//
//    @Autowired
//    public UserDetailsServiceDetails(DiscoveryClient discoveryClient) {
//        this.discoveryClient = discoveryClient;
//    }
//
//
//    @PostConstruct
//    public void init() {
//            try {
//                // Fetch the first available instance of auth-service
//                var instances = discoveryClient.getInstances("easybuy-user-details");
//
//                if (instances == null || instances.isEmpty()) {
//                    throw new RuntimeException("user-details-service not found in service registry");
//                }
//
//                // Get the first instance and populate the fields
//                var authServiceInstance = instances.get(0);
//                this.authServiceHost = authServiceInstance.getHost();
//                this.authServiceAddress = authServiceInstance.getUri().toString();
//                this.authServicePort = String.valueOf(authServiceInstance.getPort());
//
//            } catch (Exception e) {
//                throw new RuntimeException("Error initializing AuthServiceDetails", e);
//            }
//        }
//    public String getAuthServiceHost() {
//        return authServiceHost;
//    }
//
//    public String getAuthServiceAddress() {
//        return authServiceAddress;
//    }
//
//    public String getAuthServicePort() {
//        return authServicePort;
//    }
//}
