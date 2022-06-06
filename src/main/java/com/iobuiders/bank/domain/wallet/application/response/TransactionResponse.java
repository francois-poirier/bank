package com.iobuiders.bank.domain.wallet.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponse {
    private BigDecimal amount;
    private String transactionType;
    private UUID walletSrc;
    private UUID walletTarget;
}
