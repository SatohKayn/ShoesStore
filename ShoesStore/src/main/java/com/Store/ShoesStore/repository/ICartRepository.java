package com.Store.ShoesStore.repository;

import com.Store.ShoesStore.enity.Cart;
import com.Store.ShoesStore.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(User user);
}
