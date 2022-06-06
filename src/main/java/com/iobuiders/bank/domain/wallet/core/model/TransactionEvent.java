package com.iobuiders.bank.domain.wallet.core.model;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

public class TransactionEvent extends ApplicationEvent {

    @Getter
    private Transaction transaction;

    public TransactionEvent(Object src, Wallet source, Wallet target, BigDecimal amount, TransactionType transactionType) {
        super(src);
        this.transaction = new Transaction(source, target, amount,transactionType);
    }

}
