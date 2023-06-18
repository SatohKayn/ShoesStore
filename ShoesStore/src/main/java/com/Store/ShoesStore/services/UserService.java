package com.Store.ShoesStore.services;

import com.Store.ShoesStore.enity.Cart;
import com.Store.ShoesStore.enity.Product;
import com.Store.ShoesStore.enity.User;
import com.Store.ShoesStore.repository.ICartRepository;
import com.Store.ShoesStore.repository.IRoleRepository;
import com.Store.ShoesStore.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private CartService cartService;
    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId,roleId);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);

            user.setCart(cart);
            userRepository.save(user);
        }
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public void editUser(User newUser){
        userRepository.save(newUser);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
