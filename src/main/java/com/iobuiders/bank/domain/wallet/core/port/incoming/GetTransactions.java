package com.iobuiders.bank.domain.wallet.core.port.incoming;

import com.iobuiders.bank.domain.wallet.application.query.TransactionResponse;

import java.util.List;
import java.util.UUID;

public interface GetTransactions {

    List<TransactionResponse> handle(UUID walletId);
}
