package com.iobuiders.bank.domain.wallet.application;

import com.iobuiders.bank.domain.wallet.application.query.TransactionResponse;
import com.iobuiders.bank.domain.wallet.core.port.incoming.GetTransactions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "transactions", description = "the transactions API")
@RequestMapping("/api/v1/transactions")
@Validated
public class TransactionController {

    private final GetTransactions getTransactions;

    @Operation(summary = "Get all transactions by wallet id", description = "Get all transactions by wallet id", tags = { "transactions" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionResponse.class)))) })
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(@Parameter(description = "wallet id to be obtained. Cannot be empty.", required = true) @Valid @NotEmpty @RequestParam(name = "walletId", required = true) final UUID walletId) {
        return new ResponseEntity<>( getTransactions.handle(walletId), HttpStatus.OK);
    }
}
