package com.iobuiders.bank.domain.wallet.core;

import com.iobuiders.bank.domain.wallet.core.model.Transaction;
import com.iobuiders.bank.domain.wallet.core.model.TransactionType;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.LoadTransactionPort;
import com.iobuiders.bank.domain.wallet.core.port.outgoing.UpdateTransactionPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionFacadeTest {

    public static final UUID TRANSACTION_ID = java.util.UUID.randomUUID();
    public static final UUID WALLET_ID = java.util.UUID.randomUUID();
    private UpdateTransactionPort updateTransactionPort;
    private LoadTransactionPort loadTransactionPort;
    private TransactionFacade sut;

    @BeforeEach
    public void setUp() {
        this.updateTransactionPort = Mockito.mock(UpdateTransactionPort.class);
        this.loadTransactionPort  = Mockito.mock(LoadTransactionPort.class);
        this.sut = new TransactionFacade(updateTransactionPort,loadTransactionPort);
    }

    @Test
    void shouldBeCreateTransaction() {
        // given
        when(updateTransactionPort.save(any())).thenReturn(TRANSACTION_ID);
        // when
        var response=  this.sut.create(any());
        // then
        assertEquals(TRANSACTION_ID, response.getAsUuid());
    }

    @Test
    void shouldBeHandleWalletHistory() {
        // given
        List<Transaction> transactions = getTransactions();
        when( loadTransactionPort.getTransactions(any())).thenReturn(transactions);
        // when
        var response = this.sut.handle(WALLET_ID);
        // then
        assertEquals(1,response.size());
    }

    private List<Transaction> getTransactions() {
        return Arrays.asList(Transaction.builder()
                          .id(TRANSACTION_ID)
                          .amount(BigDecimal.TEN)
                          .type(TransactionType.DEPOSIT)
                          .build());
    }


}
