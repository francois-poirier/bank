package com.iobuiders.bank.domain.wallet.core.port.incoming;

import java.math.BigDecimal;
import java.util.UUID;

public interface GetBalance {

    BigDecimal getBalance(UUID id);
}
