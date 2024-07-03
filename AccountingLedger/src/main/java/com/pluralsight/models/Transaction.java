package com.pluralsight.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int customerId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    // Constructors
    public Transaction() {}

    public Transaction(int id, int customerId, BigDecimal amount, LocalDateTime transactionDate) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Transaction(int customerId, BigDecimal amount, LocalDateTime transactionDate) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
