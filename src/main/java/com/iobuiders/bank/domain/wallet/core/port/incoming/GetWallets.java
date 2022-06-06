package com.iobuiders.bank.domain.wallet.core.port.incoming;

import com.iobuiders.bank.domain.wallet.application.response.WalletResponse;

import java.util.List;

public interface GetWallets {
    public List<WalletResponse> getWallets();
}
