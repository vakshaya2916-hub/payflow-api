package com.payflow.service;

import com.payflow.entity.User;
import com.payflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Spring Boot creates the UserRepository bean at startup and injects it here using dependency injection.
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUpiId(String upiId) {
        return userRepository.findByUpiId(upiId);
    }

    public List<User> findUsersWithBalanceGreaterThan(BigDecimal minBalance) {
        return userRepository.findUsersWithBalanceGreaterThan(minBalance);
    }
}
