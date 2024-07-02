package com.pluralsight.models;

public class TransactionType {
    private int id;
    private String type;

    // Constructors
    public TransactionType() {}

    public TransactionType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public TransactionType(String type) {
        this.type = type;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
