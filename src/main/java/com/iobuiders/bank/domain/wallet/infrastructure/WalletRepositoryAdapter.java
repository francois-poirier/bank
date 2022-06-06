package com.iobuiders.bank.domain.wallet.infrastructure;

import com.iobuiders.bank.domain.wallet.core.model.Wallet;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.LoadWalletPort;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.UpdateWalletPort;
import com.iobuiders.bank.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletRepositoryAdapter implements UpdateWalletPort, LoadWalletPort {

    private final WalletRepository walletRepository;

    @Override
    public UUID save(Wallet wallet) {
        Wallet savedWallet = walletRepository.save(wallet);
        return savedWallet.getId();
    }

    @Override
    public Wallet getWallet(UUID id) {
        return walletRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(String.format("The wallet %s doesn't no exist", id)));
    }

    @Override
    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }
}
