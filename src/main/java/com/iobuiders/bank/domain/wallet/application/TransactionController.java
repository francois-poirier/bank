package com.iobuiders.bank.domain.wallet.application;

import com.iobuiders.bank.domain.wallet.application.query.TransactionResponse;
import com.iobuiders.bank.domain.wallet.core.port.incoming.GetTransactions;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "transactions", description = "the transactions API")
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final GetTransactions getTransactions;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestParam(name = "walletId", required = true) final UUID walletId) {
        return new ResponseEntity<>( getTransactions.handle(walletId), HttpStatus.OK);
    }
}
