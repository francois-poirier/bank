package com.iobuiders.bank.domain.wallet.core.port.incoming;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransferMoney {

    void transfer(UUID srcId, UUID tgtId, BigDecimal amount);
}
