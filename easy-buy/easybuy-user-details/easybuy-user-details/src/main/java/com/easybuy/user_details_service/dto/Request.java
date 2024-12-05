package com.easybuy.user_details_service.dto;

public class Request {
      private String username;
      private String password;
      private String userRole;
      private String emailId;
      public Request() {
      }

      public String getEmailId() {
            return emailId;
      }

      public void setEmailId(String emailId) {
            this.emailId = emailId;
      }

      public String getUserRole() {
            return userRole;
      }

      public void setUserRole(String userRole) {
            this.userRole = userRole;
      }

      public String getUsername() {
            return username;
      }

      public void setUsername(String username) {
            this.username = username;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      @Override
      public String toString() {
            return "Request{" +
                     "username='" + username + '\'' +
                     ", password='" + password + '\'' +
                     ", userRole='" + userRole + '\'' +
                     '}';
      }
}
