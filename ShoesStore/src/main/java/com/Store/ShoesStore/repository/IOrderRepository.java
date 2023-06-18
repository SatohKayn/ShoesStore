package com.Store.ShoesStore.repository;

import com.Store.ShoesStore.enity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    // Additional methods if needed
}
