package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

// No need implementation, just one interface, and you have CRUD!
public interface WalletRepository extends MongoRepository<Wallet, String> {

    Wallet findByWalletNumberId(String id);
}
