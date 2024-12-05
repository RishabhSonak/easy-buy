package com.easybuy_auth_service.dto;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

      private String error;
      private HttpStatus status;
      private String errorDetails;

      public String getError() {
            return error;
      }

      public HttpStatus getStatus() {
            return status;
      }

      public void setStatus(HttpStatus status) {
            this.status = status;
      }

      public void setError(String error) {
            this.error = error;
      }

      public String getErrorDetails() {
            return errorDetails;
      }

      public void setErrorDetails(String errorDetails) {
            this.errorDetails = errorDetails;
      }

      @Override
      public String toString() {
            return "ErrorDetails{" +
                     "error='" + error + '\'' +
                     ", status='" + status + '\'' +
                     ", errorDetails='" + errorDetails + '\'' +
                     '}';
      }
}
