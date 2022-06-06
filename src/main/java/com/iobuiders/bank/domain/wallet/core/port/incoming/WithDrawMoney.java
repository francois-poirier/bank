package com.iobuiders.bank.domain.wallet.core.port.incoming;

import java.math.BigDecimal;
import java.util.UUID;

public interface WithDrawMoney {
    void withDraw(UUID id, BigDecimal amount);
}
