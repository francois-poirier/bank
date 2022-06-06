package com.iobuiders.bank.domain.wallet.core.port.outgoing;

import com.iobuiders.bank.domain.wallet.core.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface LoadTransactionPort {
    List<Transaction> getTransactions(UUID walletId);
}
