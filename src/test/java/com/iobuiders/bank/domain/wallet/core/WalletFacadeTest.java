package com.iobuiders.bank.domain.wallet.core;

import com.iobuiders.bank.domain.wallet.core.event.TransactionPublisher;
import com.iobuiders.bank.domain.wallet.core.model.Wallet;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.LoadWalletPort;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.UpdateWalletPort;
import com.iobuiders.bank.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WalletFacadeTest {

    public static final UUID WALLET_ID = java.util.UUID.randomUUID();
    public static final UUID WALLET_SRC_ID = java.util.UUID.randomUUID();
    public static final UUID WALLET_TGT_ID = java.util.UUID.randomUUID();
    public static final BigDecimal BALANCE = new BigDecimal(3000);
    public static final BigDecimal HIGH_AMOUNT = new BigDecimal(30000);
    public static final BigDecimal LOW_AMOUNT = new BigDecimal(300);
    private UpdateWalletPort updateWalletPort;
    private LoadWalletPort loadWalletPort;
    private TransactionPublisher transactionPublisher;
    private WalletFacade sut;

    @BeforeEach
    public void setUp() {
        this.updateWalletPort = Mockito.mock(UpdateWalletPort.class);
        this.loadWalletPort  = Mockito.mock(LoadWalletPort.class);
        this.transactionPublisher = Mockito.mock(TransactionPublisher.class);
        this.sut = new WalletFacade(updateWalletPort,loadWalletPort,transactionPublisher);
    }

    @Test
    void shouldBeGetBalance() {
        // given
        Wallet wallet =  getWallet();
        when(loadWalletPort.getWallet(WALLET_ID)).thenReturn(wallet);
        // when
        var response = this.sut.getBalance(WALLET_ID);
        // then
        assertEquals(BALANCE,response);
    }

    @Test
    void shouldBeDeposit() {
        // given
        Wallet wallet =  getWallet();
        when(loadWalletPort.getWallet(WALLET_ID)).thenReturn(wallet);
        when(updateWalletPort.save(any())).thenReturn(WALLET_ID);
        doNothing().when(transactionPublisher).publish(any());
        // when
        this.sut.deposit(WALLET_ID, HIGH_AMOUNT);
        // then
        verify(loadWalletPort, times(1)).getWallet(WALLET_ID);
        verify(updateWalletPort, times(1)).save(any());
        verify(transactionPublisher, times(1)).publish(any());
    }


    @Test
    void shouldBeWithDraw() {
        // given
        Wallet wallet =  getWallet();
        when(loadWalletPort.getWallet(WALLET_ID)).thenReturn(wallet);
        when(updateWalletPort.save(any())).thenReturn(WALLET_ID);
        doNothing().when(transactionPublisher).publish(any());
        // when
        this.sut.withDraw(WALLET_ID, HIGH_AMOUNT);
        // then
        verify(loadWalletPort, times(1)).getWallet(WALLET_ID);
        verify(updateWalletPort, times(1)).save(any());
        verify(transactionPublisher, times(1)).publish(any());
    }

    @Test
    void shouldBeNotTransfer() {
        // given
        Wallet walletSrc =  getWalletSrc();
        when(loadWalletPort.getWallet(WALLET_SRC_ID)).thenReturn(walletSrc);
        Wallet walletTgt =  getWalletTarget();
        when(loadWalletPort.getWallet(WALLET_TGT_ID)).thenReturn(walletTgt);
        // when
        assertThrows(BusinessException.class, () -> {
            this.sut.transfer(WALLET_SRC_ID,WALLET_TGT_ID, HIGH_AMOUNT);
        });
        // then
        verify(loadWalletPort, times(1)).getWallet(WALLET_SRC_ID);
        verify(loadWalletPort, times(1)).getWallet(WALLET_TGT_ID);
    }

    @Test
    void shouldBeTransfer() {
        // given
        Wallet walletSrc =  getWalletSrc();
        when(loadWalletPort.getWallet(WALLET_SRC_ID)).thenReturn(walletSrc);
        Wallet walletTgt =  getWalletTarget();
        when(loadWalletPort.getWallet(WALLET_TGT_ID)).thenReturn(walletTgt);
        when(updateWalletPort.save(any())).thenReturn(WALLET_SRC_ID);
        when(updateWalletPort.save(any())).thenReturn(WALLET_TGT_ID);
        // when
        this.sut.transfer(WALLET_SRC_ID,WALLET_TGT_ID, LOW_AMOUNT);
        // then
        verify(loadWalletPort, times(1)).getWallet(WALLET_SRC_ID);
        verify(loadWalletPort, times(1)).getWallet(WALLET_TGT_ID);
        verify(updateWalletPort, times(2)).save(any());
        verify(transactionPublisher, times(1)).publish(any());
    }

    @Test
    void shouldBeGetWallets() {
        // given
        List<Wallet> wallets = getWallets();
        when( loadWalletPort.getWallets()).thenReturn(wallets);
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

    private Wallet getWalletSrc() {
        return Wallet.builder()
                .id(WALLET_SRC_ID)
                .balance(BALANCE)
                .build();
    }

    private Wallet getWalletTarget() {
        return Wallet.builder()
                .id(WALLET_TGT_ID)
                .balance(BALANCE)
                .build();
    }

    private List<Wallet> getWallets() {
        return Arrays.asList(getWallet());
    }
}
