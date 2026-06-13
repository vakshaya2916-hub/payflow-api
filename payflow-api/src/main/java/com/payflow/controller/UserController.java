package com.payflow.controller;

import com.payflow.entity.User;
import com.payflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("POST /users with @RequestBody received: " + user);
        User savedUser = userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/demo-no-body")
    public ResponseEntity<User> registerUserWithoutRequestBody(User user) {
        System.out.println("POST /users/demo-no-body without @RequestBody received: " + user);
        User savedUser = userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/upi/{upiId}")
    public ResponseEntity<User> getUserByUpiId(@PathVariable String upiId) {
        Optional<User> user = userService.findByUpiId(upiId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/balance-above")
    public ResponseEntity<List<User>> getUsersWithBalanceAbove(@RequestParam BigDecimal amount) {
        return ResponseEntity.ok(userService.findUsersWithBalanceGreaterThan(amount));
    }
}
