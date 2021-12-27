package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.ReloadPaymentDTO;
import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private Logger log = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletService walletService;

    @RequestMapping("/")
    void log() {
        log.info("Logging from /");
    }

    @GetMapping("/all")
    public List<WalletDTO> getAllWallets() {
        log.info("All wallets requested");
        return ObjectMapperUtils.mapAll(walletService.findAll(), WalletDTO.class);
    }

    @GetMapping(value = "/byWalletNumber/{walletNumber}")
    public WalletDTO getWalletByWalletNumber(@PathVariable("walletNumber") String walletNumber) {
        log.info("Requested wallet by Wallet Number Id: " + walletNumber);
        return ObjectMapperUtils.map(walletService.findByWalletNumberId(walletNumber), WalletDTO.class);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateWallet(@RequestBody WalletDTO wallet) {
        log.info("Requested to add a new wallet with Wallet Number id: " + wallet.getWalletNumberId());
        walletService.saveOrUpdateWallet(ObjectMapperUtils.map(wallet, Wallet.class));
        return new ResponseEntity("Wallet added successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/reloadWallet")
    public ResponseEntity<?> reloadWallet(@RequestBody ReloadPaymentDTO reloadPaymentDTO) throws ExecutionException, InterruptedException {
        log.info("Requested to reload wallet with Wallet Number id: " + reloadPaymentDTO.getWalletNumberId()
                + " with an amount of " + reloadPaymentDTO.getAmount() + "  through the card "
                + reloadPaymentDTO.getCreditCard());
        double availableMoney = walletService.reloadWallet(reloadPaymentDTO).get();
        return new ResponseEntity("Available money on the Wallet after reload is " + availableMoney, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{walletNumber}")
    public ResponseEntity<?> deleteStudentByStudentNumber(@PathVariable String walletNumber) {
        log.info("Requested to delete Wallet with Wallet Number id: " + walletNumber);
        walletService.deleteWalletById(walletService.findByWalletNumberId(walletNumber).getSystemId());
        return new ResponseEntity("Wallet deleted successfully", HttpStatus.OK);
    }
}
