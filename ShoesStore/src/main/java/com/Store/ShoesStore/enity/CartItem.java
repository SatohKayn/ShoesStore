package com.Store.ShoesStore.enity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

}
