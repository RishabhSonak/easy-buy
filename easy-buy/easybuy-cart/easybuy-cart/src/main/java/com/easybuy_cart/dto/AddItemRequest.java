package com.easybuy_cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AddItemRequest {
      @NotBlank
      private String productId;

      @NotBlank
      private String productName;

      @Min(1)
      private Integer quantity;

      public String getProductId() {
            return productId;
      }

      public void setProductId(String productId) {
            this.productId = productId;
      }

      public String getProductName() {
            return productName;
      }

      public void setProductName(String productName) {
            this.productName = productName;
      }

      public Integer getQuantity() {
            return quantity;
      }

      public void setQuantity(Integer quantity) {
            this.quantity = quantity;
      }

      @Override
      public String toString() {
            return "AddItemRequest{" +
                     "productId='" + productId + '\'' +
                     ", productName='" + productName + '\'' +
                     ", quantity=" + quantity +
                     '}';
      }
}
