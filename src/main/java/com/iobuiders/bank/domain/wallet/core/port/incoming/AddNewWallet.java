package com.iobuiders.bank.domain.wallet.core.port.incoming;

import com.iobuiders.bank.domain.user.core.model.User;
public interface AddNewWallet {
    void create(User user);
}
