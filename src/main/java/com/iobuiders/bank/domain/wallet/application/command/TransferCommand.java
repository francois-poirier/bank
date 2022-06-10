package com.iobuiders.bank.domain.wallet.application.command;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferCommand {
    @Getter
    @NotEmpty
    private BigDecimal amount;
    @Getter
    @NotEmpty
    private UUID walletSrc;
    @Getter
    @NotEmpty
    private UUID walletTarget;
}
