package com.Store.ShoesStore.enity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.Store.ShoesStore.validator.annotation.ValidCategoryId;
import com.Store.ShoesStore.validator.annotation.ValidUserId;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int currentQuantity;
    private String origin;
    private double costPrice;
    private double salePrice;
    private String image;
    private boolean is_activated;
    public boolean isIs_activated() {
        return is_activated;
    }

    public void setIs_activated(boolean is_activated) {
        this.is_activated = is_activated;
    }
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;
}
