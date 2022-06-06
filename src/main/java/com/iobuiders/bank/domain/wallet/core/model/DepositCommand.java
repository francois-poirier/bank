package com.iobuiders.bank.domain.wallet.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositCommand {
    @Getter
    BigDecimal amount;
}
