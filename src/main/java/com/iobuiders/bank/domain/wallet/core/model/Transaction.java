package com.iobuiders.bank.domain.wallet.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transaction", schema = "bank")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "Transaction amount must be provided")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne(targetEntity = Wallet.class, fetch = FetchType.EAGER)
    private Wallet source;

    @ManyToOne(targetEntity = Wallet.class, fetch = FetchType.EAGER)
    private Wallet target;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(Wallet source, Wallet target, BigDecimal amount, TransactionType transactionType) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.type = transactionType;
    }

}
