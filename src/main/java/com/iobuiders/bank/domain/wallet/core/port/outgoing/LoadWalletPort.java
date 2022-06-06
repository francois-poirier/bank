package com.iobuiders.bank.domain.wallet.core.port.outgoing;

import com.iobuiders.bank.domain.wallet.core.model.Wallet;

import java.util.List;
import java.util.UUID;

public interface LoadWalletPort {

    public Wallet getWallet(UUID id);

    public List<Wallet> getWallets();
}
