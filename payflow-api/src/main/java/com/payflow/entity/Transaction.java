package com.payflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderUpiId;
    private String receiverUpiId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(Long id, String senderUpiId, String receiverUpiId, BigDecimal amount, LocalDateTime createdAt) {
        this.id = id;
        this.senderUpiId = senderUpiId;
        this.receiverUpiId = receiverUpiId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderUpiId() {
        return senderUpiId;
    }

    public void setSenderUpiId(String senderUpiId) {
        this.senderUpiId = senderUpiId;
    }

    public String getReceiverUpiId() {
        return receiverUpiId;
    }

    public void setReceiverUpiId(String receiverUpiId) {
        this.receiverUpiId = receiverUpiId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderUpiId='" + senderUpiId + '\'' +
                ", receiverUpiId='" + receiverUpiId + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
