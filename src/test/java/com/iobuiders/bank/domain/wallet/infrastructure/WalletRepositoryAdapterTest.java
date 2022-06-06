package com.iobuiders.bank.domain.wallet.infrastructure;

import com.iobuiders.bank.domain.wallet.core.model.Wallet;
import com.iobuiders.bank.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WalletRepositoryAdapterTest {
    public static final UUID WALLET_ID = java.util.UUID.randomUUID();
    public static final BigDecimal BALANCE = new BigDecimal(3000);
    private WalletRepository walletRepository;
    private WalletRepositoryAdapter sut;

    @BeforeEach
    public void setUp() {
        this.walletRepository = Mockito.mock(WalletRepository.class);
        this.sut = new WalletRepositoryAdapter(walletRepository);
    }

    @Test
    void shouldBeCreateWallet () {
        // given
        Wallet wallet = getWallet();
        when(walletRepository.save(wallet)).thenReturn(wallet);
        // when
        var response = this.sut.save(wallet);
        // then
        assertEquals(WALLET_ID, response);
    }

    @Test
    void shouldBeNotGetWallet(){
        // given
        when(walletRepository.findById(WALLET_ID)).thenReturn(Optional.empty());
        // when
        assertThrows(EntityNotFoundException.class, () -> {
            this.sut.getWallet(WALLET_ID);
        });
        // then
        verify(walletRepository, times(1)).findById(WALLET_ID);
    }

    @Test
    void shouldBeGetWallet(){
        // given
        Wallet wallet = getWallet();
        when(walletRepository.findById(WALLET_ID)).thenReturn(Optional.of(wallet));
        // when
        var response =  this.sut.getWallet(WALLET_ID);
        // then
        verify(walletRepository, times(1)).findById(WALLET_ID);
        assertEquals(WALLET_ID, response.getId());
    }

    @Test
    void shouldBeGetWallets() {
        // given
        List<Wallet> wallets = getWallets();
        when( walletRepository.findAll()).thenReturn(wallets);
        // when
        var response = this.sut.getWallets();
        // then
        assertEquals(1,response.size());
    }


    private Wallet getWallet() {
        return Wallet.builder()
                .id(WALLET_ID)
                .balance(BALANCE)
                .build();
    }

    private List<Wallet> getWallets() {
        return Arrays.asList(getWallet());
    }
}
