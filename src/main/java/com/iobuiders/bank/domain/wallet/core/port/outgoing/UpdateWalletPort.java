package com.iobuiders.bank.domain.wallet.core.port.outgoing;

import com.iobuiders.bank.domain.wallet.core.model.Wallet;

import java.util.UUID;

public interface UpdateWalletPort {
    UUID save(Wallet wallet);
}
