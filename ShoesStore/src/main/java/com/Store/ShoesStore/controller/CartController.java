package com.Store.ShoesStore.controller;


import com.Store.ShoesStore.enity.*;
import com.Store.ShoesStore.services.CartService;
import com.Store.ShoesStore.services.ProductService;
import com.Store.ShoesStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @GetMapping
    public String getCartByUser(Model model, Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        Cart cart = cartService.getCartByUser(user);
        List<CartItem> cartItems = cart.getItems();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cart.getTotal());
        model.addAttribute("cartDiscount", cart.getDiscount());
        return "cart/cart_item";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(Authentication authentication,
                                                @RequestParam("productId") Long productId,
                                                @RequestParam("quantity") int quantity) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        Product product = productService.getProductById(productId);

        cartService.addItemToCart(user, product, quantity);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeItemFromCart(Authentication authentication,
                                                     @RequestParam("productId") Long productId) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        Product product = productService.getProductById(productId);

        cartService.removeItemFromCart(user, product);
        return ResponseEntity.ok("Item removed from cart successfully");
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCart(Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        cartService.clearCart(user);
        return ResponseEntity.ok("Cart cleared successfully");
    }
/*    @GetMapping("/checkout")
    public String getOrder(Authentication authentication, Model model){
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        Cart cart = cartService.getCartByUser(user);
        model.addAttribute("cartTotal", cart.getTotal());
        return "cart/checkout";
    }
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(Authentication authentication) {
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        cartService.checkout(user);
        return ResponseEntity.ok("Checkout completed successfully");
    }*/
}
