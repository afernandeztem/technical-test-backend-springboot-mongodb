package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.util.ObjectMapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class WalletApplicationIT {

    Wallet wallet;
    @Autowired
    private WalletService walletService;

    @BeforeEach
    void setUp() {
        wallet = new Wallet("61c8377bc8af4074b7fb8008", "12341234", 400);
    }

    @Test
    public void emptyTest() {
    }

    @Test
    public void walletDTOMappingIsCorrect() {
        WalletDTO walletDTO = ObjectMapperUtils.map(wallet, WalletDTO.class);
        assertAll("Wallet Mapping is working properly",
                () -> assertEquals(wallet.getWalletNumberId(), walletDTO.getWalletNumberId()),
                () -> assertEquals(wallet.getBalance(), walletDTO.getBalance()),
                () -> assertEquals(wallet.getSystemId(), walletDTO.getSystemId())
        );
    }

    @Test
    void updateWalletPostiveAmount() {
        wallet.updateBalance(100);
        assertEquals(500, wallet.getBalance());
    }

    @Test
    void updateWalletNegativeAmount() {
        wallet.updateBalance(-100);
        assertEquals(300, wallet.getBalance());
    }

}
