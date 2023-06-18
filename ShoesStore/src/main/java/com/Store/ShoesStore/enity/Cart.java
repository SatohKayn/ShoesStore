package com.Store.ShoesStore.enity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    public double getTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getProduct().getCostPrice() * item.getQuantity();
        }
        return total;
    }
    public double getDiscount() {
        double total = 0.0;
        for (CartItem item : items) {
            total += (item.getProduct().getCostPrice() - item.getProduct().getSalePrice()) * item.getQuantity();
        }
        return total;
    }
}