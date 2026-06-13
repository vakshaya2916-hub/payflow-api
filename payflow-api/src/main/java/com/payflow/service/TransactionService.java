package com.payflow.service;

import com.payflow.entity.Transaction;
import com.payflow.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Spring Boot creates the TransactionRepository bean at startup and injects it here using dependency injection.
    public Transaction sendMoney(Transaction transaction) {
        if (transaction.getCreatedAt() == null) {
            transaction.setCreatedAt(LocalDateTime.now());
        }
        return transactionRepository.save(transaction);
    }
}
