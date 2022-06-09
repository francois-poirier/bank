package com.iobuiders.bank.domain.wallet.core;

import com.iobuiders.bank.domain.user.core.model.User;
import com.iobuiders.bank.domain.wallet.application.query.WalletResponse;
import com.iobuiders.bank.domain.wallet.core.event.TransactionPublisher;
import com.iobuiders.bank.domain.wallet.core.model.TransactionEvent;
import com.iobuiders.bank.domain.wallet.core.model.TransactionType;
import com.iobuiders.bank.domain.wallet.core.model.Wallet;
import com.iobuiders.bank.domain.wallet.core.port.incoming.*;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.LoadWalletPort;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.UpdateWalletPort;
import com.iobuiders.bank.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class WalletFacade implements AddNewWallet, DepositMoney, GetBalance, TransferMoney, WithDrawMoney, GetWallets {

    private final UpdateWalletPort updateWalletPort;
    private final LoadWalletPort loadWalletPort;

    private final TransactionPublisher transactionPublisher;
    @Override
    public void create(User user) {
        updateWalletPort.save(new Wallet(BigDecimal.ZERO, user));
    }

    @Override
    public BigDecimal getBalance(UUID id) {
        final Wallet wallet = loadWalletPort.getWallet(id);
        return wallet.getBalance();
    }

    @Override
    public void deposit(UUID id, BigDecimal amount) {
        Wallet wallet = loadWalletPort.getWallet(id);
        wallet.deposit(amount);
        updateWalletPort.save(wallet);
        transactionPublisher.publish(new TransactionEvent(this, wallet, wallet, amount, TransactionType.DEPOSIT));
    }

    @Override
    public void transfer(UUID srcId, UUID tgtId, BigDecimal amount) {
        Wallet walletSrc = loadWalletPort.getWallet(srcId);
        Wallet walletTgt = loadWalletPort.getWallet(tgtId);
        if (!walletSrc.withdraw(amount)) {
            throw new BusinessException(String.format("The wallet %s doesn't have enough funds", srcId));
        }
        walletTgt.deposit(amount);
        updateWalletPort.save(walletSrc);
        updateWalletPort.save(walletTgt);
        transactionPublisher.publish(new TransactionEvent(this, walletSrc, walletTgt, amount,TransactionType.TRANSFER));
    }

    @Override
    public void withDraw(UUID id, BigDecimal amount) {
        Wallet wallet = loadWalletPort.getWallet(id);
        wallet.withdraw(amount);
        updateWalletPort.save(wallet);
        transactionPublisher.publish(new TransactionEvent(this, wallet, wallet, amount,TransactionType.WITHDRAW));
    }

    @Override
    public List<WalletResponse> getWallets() {
        return loadWalletPort.getWallets().stream().map(w->toDto(w)).collect(Collectors.toList());
    }

    private WalletResponse toDto(Wallet w) {
        return WalletResponse.builder()
                             .id(w.getId())
                             .balance(w.getBalance())
                             .build();
    }
}
