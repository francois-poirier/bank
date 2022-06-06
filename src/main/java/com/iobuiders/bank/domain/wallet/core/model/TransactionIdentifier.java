package com.iobuiders.bank.domain.wallet.core.model;

import java.util.UUID;

public class TransactionIdentifier {
    private final UUID id;

    public TransactionIdentifier(UUID id) {
        this.id = id;
    }

    public UUID getAsUuid(){
        return id;
    }
}
