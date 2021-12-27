package com.playtomic.tests.wallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WalletDTO {
    // In the case, we want to return the id, we just need to serialize it removing the @JsonIgnore tag.
    @JsonIgnore
    private String systemId;
    private String walletNumberId;
    private double balance;

    public WalletDTO() {
    }

    public WalletDTO(String systemId, String walletNumberId, double balance) {
        this.systemId = systemId;
        this.walletNumberId = walletNumberId;
        this.balance = balance;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getWalletNumberId() {
        return walletNumberId;
    }

    public void setWalletNumberId(String walletNumberId) {
        this.walletNumberId = walletNumberId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // This amount can be positive or negative
    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public String toString() {
        return "Wallet with identifier " + walletNumberId + " has a value of " + balance;
    }
}

