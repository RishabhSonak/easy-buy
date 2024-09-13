package com.easybuy_cart.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> items;

    @Column(name = "total_amount")
    private Double totalAmount;
    public Cart() {
        this.items = new ArrayList<>(); // Initialize only for new carts, not for existing ones fetched from DB
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
