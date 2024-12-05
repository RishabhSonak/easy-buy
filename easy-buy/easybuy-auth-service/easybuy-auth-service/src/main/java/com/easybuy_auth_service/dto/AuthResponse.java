package com.easybuy_auth_service.dto;

public class AuthResponse {
      private String username;
      private String JWT;

      public AuthResponse() {
      }

      public String getUsername() {
            return username;
      }

      public void setUsername(String username) {
            this.username = username;
      }


      public String getJWT() {
            return JWT;
      }

      public void setJWT(String JWT) {
            this.JWT = JWT;
      }

      @Override
      public String toString() {
            return "CompleteAuthResponse{" +
                     "username='" + username + '\'' +
                     ", JWT='" + JWT + '\'' +
                     '}';
      }
}
