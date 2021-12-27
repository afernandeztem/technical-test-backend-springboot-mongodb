package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.dto.ReloadPaymentDTO;
import com.playtomic.tests.wallet.model.Wallet;

import java.util.List;

public interface WalletService {

    List<Wallet> findAll();

    Wallet findByWalletNumberId(String id);

    Wallet saveOrUpdateWallet(Wallet wallet);

    // Delete method added for testing
    void deleteWalletById(String id);

    Wallet reloadWallet(ReloadPaymentDTO reloadPaymentDTO);
}
