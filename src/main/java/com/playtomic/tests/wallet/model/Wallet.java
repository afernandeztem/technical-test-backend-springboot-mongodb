package com.playtomic.tests.wallet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wallets")
public class Wallet {
    @Id
    private String systemId;
    /**
     * I don't think it is appropriate for the user to know the id within
     * our database, so I have created a public identifier for the wallet (walletNumberId).
     */
    private String walletNumberId;
    private double balance;

    public Wallet() {
    }

    public Wallet(String systemId, String walletNumberId, double balance) {
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
}
