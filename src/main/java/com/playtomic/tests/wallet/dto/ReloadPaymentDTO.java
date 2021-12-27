package com.playtomic.tests.wallet.dto;

import java.math.BigDecimal;

public class ReloadPaymentDTO {

    private String walletNumberId;
    private BigDecimal amount;
    private String creditCard;

    public ReloadPaymentDTO() {
    }

    public ReloadPaymentDTO(String walletNumberId, BigDecimal amount, String creditCard) {
        this.walletNumberId = walletNumberId;
        this.amount = amount;
        this.creditCard = creditCard;
    }

    public String getWalletNumberId() {
        return walletNumberId;
    }

    public void setWalletNumberId(String walletNumberId) {
        this.walletNumberId = walletNumberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
