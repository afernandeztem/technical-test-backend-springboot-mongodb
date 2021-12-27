package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.util.ObjectMapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class WalletApplicationITTest {

    Wallet wallet;
    @Autowired
    private WalletService walletService;

    @Resource
    private WalletRepository walletRepository;

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
    public void getValidWalletById() {
        walletRepository.save(wallet);
        Wallet searchedWallet = walletService.findByWalletNumberId(wallet.getWalletNumberId());
        assertAll("Wallet searching is working properly",
                () -> assertEquals(wallet.getWalletNumberId(), searchedWallet.getWalletNumberId()),
                () -> assertEquals(wallet.getBalance(), searchedWallet.getBalance()),
                () -> assertEquals(wallet.getSystemId(), searchedWallet.getSystemId())
        );
    }

    @Test
    public void getWalletByInvalidId() {
        walletRepository.save(wallet);
        assertThrows(RuntimeException.class, () -> {
            walletService.findByWalletNumberId("123123");
        });
    }


    @Test
    void updateWalletPositiveAmount() {
        wallet.updateBalance(100);
        assertEquals(500, wallet.getBalance());
    }

    @Test
    void updateWalletNegativeAmount() {
        wallet.updateBalance(-500);
        assertEquals(-100, wallet.getBalance());
    }

}
