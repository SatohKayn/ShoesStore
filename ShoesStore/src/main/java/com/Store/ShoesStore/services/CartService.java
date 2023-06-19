package com.Store.ShoesStore.services;

import com.Store.ShoesStore.enity.*;
import com.Store.ShoesStore.repository.ICartRepository;
import com.Store.ShoesStore.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IOrderRepository orderRepository;

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public void addItemToCart(User user, Product product, int quantity) {
        Cart cart = getCartByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartRepository.save(cart);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);

        }
        cartRepository.save(cart);
    }

    public void removeItemFromCart(User user, Product product) {
        Cart cart = getCartByUser(user);

        Optional<CartItem> itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst();

        itemToRemove.ifPresent(item -> {
            cart.getItems().remove(item);
            cartRepository.save(cart);
        });
    }

    public void clearCart(User user) {
        Cart cart = getCartByUser(user);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    public void checkout(User user) {
        Cart cart = getCartByUser(user);

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotal() - cart.getDiscount());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        orderRepository.save(order);

        clearCart(user);
    }
}
