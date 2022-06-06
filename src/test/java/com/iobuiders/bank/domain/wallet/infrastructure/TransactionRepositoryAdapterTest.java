package com.iobuiders.bank.domain.wallet.infrastructure;

import com.iobuiders.bank.domain.wallet.core.model.Transaction;
import com.iobuiders.bank.domain.wallet.core.model.TransactionType;
import com.iobuiders.bank.domain.wallet.core.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TransactionRepositoryAdapterTest {

    public static final UUID WALLET_ID = java.util.UUID.randomUUID();
    public static final BigDecimal BALANCE = new BigDecimal(3000);
    public static final UUID TRANSACTION_ID = java.util.UUID.randomUUID();
    private TransactionRepository transactionRepository;
    private TransactionRepositoryAdapter sut;

    @BeforeEach
    public void setUp() {
        this.transactionRepository = Mockito.mock(TransactionRepository.class);
        this.sut = new TransactionRepositoryAdapter(transactionRepository);
    }

    @Test
    void shouldBeCreateTransaction () {
        // given
        Transaction transaction = getTransaction();
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        // when
        var response = this.sut.save(transaction);
        // then
        assertEquals(TRANSACTION_ID, response);
    }

    @Test
    void shouldBeGetTransactions() {
        // given
        List<Transaction> transactions = getTransactions();
        when(transactionRepository.findAll()).thenReturn(transactions);
        // when
        var response =this.sut.getTransactions(WALLET_ID);
        // then
        assertEquals(1,response.size());
    }

    private List<Transaction> getTransactions() {
        return Arrays.asList(getTransaction());
    }

    private Transaction getTransaction() {
        Wallet wallet = getWallet();
        return Transaction.builder()
                .id(TRANSACTION_ID)
                .amount(BigDecimal.TEN)
                .type(TransactionType.DEPOSIT)
                .source(wallet)
                .target(wallet)
                .build();
    }

    private Wallet getWallet() {
        return Wallet.builder()
                .id(WALLET_ID)
                .balance(BALANCE)
                .build();
    }

}
