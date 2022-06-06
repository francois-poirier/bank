package com.iobuiders.bank.domain.wallet.infrastructure;

import com.iobuiders.bank.domain.wallet.core.model.Transaction;
import com.iobuiders.bank.domain.wallet.core.model.Wallet;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.LoadTransactionPort;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.UpdateTransactionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements UpdateTransactionPort, LoadTransactionPort {

    private final TransactionRepository transactionRepository;

    @Override
    public UUID save(Transaction transaction) {
        Transaction savedTransaction =  transactionRepository.save(transaction);
        return  savedTransaction.getId();
    }

    @Override
    public List<Transaction> getTransactions(UUID walletId) {
        var transactions= transactionRepository.findAll();
        List<Transaction> filtered = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            Wallet source = transaction.getSource();
            Wallet target = transaction.getTarget();
            boolean match = false;
            if (source != null) {
                match = source.getId().equals(walletId);
            }
            if (target != null && !match) {
                match = target.getId().equals(walletId);
            }
            if (match) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }
}
