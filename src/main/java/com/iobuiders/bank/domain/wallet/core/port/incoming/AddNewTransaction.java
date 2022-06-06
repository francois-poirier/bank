package com.iobuiders.bank.domain.wallet.core.port.incoming;

import com.iobuiders.bank.domain.wallet.core.model.Transaction;
import com.iobuiders.bank.domain.wallet.core.model.TransactionIdentifier;

public interface AddNewTransaction {
    TransactionIdentifier create(Transaction transaction);
}
