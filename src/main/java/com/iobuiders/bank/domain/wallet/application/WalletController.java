package com.iobuiders.bank.domain.wallet.application;

import com.iobuiders.bank.domain.wallet.application.query.WalletResponse;
import com.iobuiders.bank.domain.wallet.application.command.DepositCommand;
import com.iobuiders.bank.domain.wallet.application.command.TransferCommand;
import com.iobuiders.bank.domain.wallet.application.command.WithdrawCommand;
import com.iobuiders.bank.domain.wallet.core.port.incoming.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "wallets", description = "the wallets API")
@RequestMapping("/api/v1/wallets")
public class WalletController {

    private final GetBalance getBalance;
    private final DepositMoney deposit;
    private final WithDrawMoney withdraw;
    private final TransferMoney transferMoney;

    private final GetWallets getWallets;

    @PostMapping(value = "/{walletId}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable UUID walletId, @RequestBody DepositCommand depositCommand) {
        deposit.deposit(walletId, depositCommand.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/{walletId}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable UUID walletId, @RequestBody WithdrawCommand withdrawCommand) {
        withdraw.withDraw(walletId, withdrawCommand.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferCommand transferCommand) {
        transferMoney.transfer(transferCommand.getWalletSrc(), transferCommand.getWalletTarget(), transferCommand.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID walletId) {
        return new ResponseEntity<>( getBalance.getBalance(walletId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WalletResponse>> getWallets() {
        return new ResponseEntity<>(getWallets.getWallets(), HttpStatus.OK);
    }
}
