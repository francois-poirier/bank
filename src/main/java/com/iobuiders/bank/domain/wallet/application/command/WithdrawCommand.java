package com.iobuiders.bank.domain.wallet.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawCommand {
    @Getter
    @NotEmpty
    BigDecimal amount;
}
