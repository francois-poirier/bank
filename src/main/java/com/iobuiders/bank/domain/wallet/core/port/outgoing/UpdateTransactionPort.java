package com.iobuiders.bank.domain.wallet.core.port.outgoing;

import com.iobuiders.bank.domain.wallet.core.model.Transaction;

import java.util.UUID;

public interface UpdateTransactionPort {
    UUID save(Transaction transaction);
}
