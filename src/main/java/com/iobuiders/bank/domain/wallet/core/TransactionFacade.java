package com.iobuiders.bank.domain.wallet.core;

import com.iobuiders.bank.domain.wallet.application.response.TransactionResponse;
import com.iobuiders.bank.domain.wallet.core.model.Transaction;
import com.iobuiders.bank.domain.wallet.core.model.TransactionIdentifier;
import com.iobuiders.bank.domain.wallet.core.port.incoming.AddNewTransaction;
import com.iobuiders.bank.domain.wallet.core.port.incoming.GetTransactions;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.LoadTransactionPort;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.UpdateTransactionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class TransactionFacade implements AddNewTransaction, GetTransactions {

    private final UpdateTransactionPort updateTransactionPort;
    private final LoadTransactionPort loadTransactionPort;

    @Override
    public TransactionIdentifier create(Transaction transaction) {
        return new TransactionIdentifier(updateTransactionPort.save(transaction));
    }

    @Override
    public List<TransactionResponse> handle(UUID walletId) {
        final List<Transaction> transactions = loadTransactionPort.getTransactions(walletId);
        return transactions.stream().map(t->toDto(t)).collect(Collectors.toList());
    }

    private TransactionResponse toDto(Transaction transaction) {
        return  TransactionResponse.builder()
                              .transactionType(transaction.getType().toString())
                              .amount(transaction.getAmount())
                              .walletSrc(transaction.getSource()!=null? transaction.getSource().getId():null)
                              .walletTarget(transaction.getTarget()!=null? transaction.getTarget().getId():null)
                              .build();
    }
}
