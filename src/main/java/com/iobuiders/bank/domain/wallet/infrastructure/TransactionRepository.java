package com.iobuiders.bank.domain.wallet.infrastructure;

import com.iobuiders.bank.domain.wallet.core.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
}
