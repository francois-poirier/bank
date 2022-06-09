package com.iobuiders.bank.domain.wallet.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawCommand {
    @Getter
    BigDecimal amount;
}
