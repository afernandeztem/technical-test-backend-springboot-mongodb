package com.playtomic.tests.wallet.service.impl;

import com.playtomic.tests.wallet.dto.ReloadPaymentDTO;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.StripeService;
import com.playtomic.tests.wallet.service.StripeServiceException;
import com.playtomic.tests.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private StripeService stripeService;

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet findByWalletNumberId(String id) {
        return walletRepository.findByWalletNumberId(id);
    }

    @Override
    public Wallet saveOrUpdateWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWalletById(String id) {
        walletRepository.deleteById(id);
    }

    @Override
    public Wallet reloadWallet(ReloadPaymentDTO reloadPaymentDTO) throws StripeServiceException {
        Wallet wallet = walletRepository.findByWalletNumberId(reloadPaymentDTO.getWalletNumberId());
        stripeService.charge(reloadPaymentDTO.getCreditCard(), reloadPaymentDTO.getAmount());
        wallet.updateBalance(reloadPaymentDTO.getAmount().doubleValue());
        walletRepository.save(wallet);
        return wallet;
    }

}