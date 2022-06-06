package com.iobuiders.bank.domain.wallet.infrastructure;

import com.iobuiders.bank.domain.wallet.core.model.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface WalletRepository  extends CrudRepository<Wallet, UUID> {

    public List<Wallet> findAll();
}
