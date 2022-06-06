package com.iobuiders.bank.domain.wallet.core.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferCommand {
    @Getter
    private BigDecimal amount;
    @Getter
    private UUID walletSrc;
    @Getter
    private UUID walletTarget;
}
