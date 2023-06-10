package com.Store.ShoesStore.services;

import com.Store.ShoesStore.enity.Product;
import com.Store.ShoesStore.enity.User;
import com.Store.ShoesStore.repository.IRoleRepository;
import com.Store.ShoesStore.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId,roleId);
        }
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }
}
