package com.iobuiders.bank.domain.wallet.application;

import com.iobuiders.bank.domain.user.application.command.AddUserCommand;
import com.iobuiders.bank.domain.wallet.application.query.WalletResponse;
import com.iobuiders.bank.domain.wallet.application.command.DepositCommand;
import com.iobuiders.bank.domain.wallet.application.command.TransferCommand;
import com.iobuiders.bank.domain.wallet.application.command.WithdrawCommand;
import com.iobuiders.bank.domain.wallet.core.port.incoming.*;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "wallets", description = "the wallets API")
@RequestMapping("/api/v1/wallets")
@Validated
public class WalletController {

    private final GetBalance getBalance;
    private final DepositMoney deposit;
    private final WithDrawMoney withdraw;
    private final TransferMoney transferMoney;

    private final GetWallets getWallets;

    @Operation(summary = "Deposit", description = "", tags = { "wallets" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deposit in wallet",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping(value = "/{walletId}/deposit")
    public ResponseEntity<Void> deposit(@Parameter(description = "wallet id to be obtained. Cannot be empty.", required = true) @Valid @NotEmpty @PathVariable UUID walletId,
                                        @Parameter(description="Deposit command. Cannot null or empty.",
                                                required=true, schema=@Schema(implementation = DepositCommand.class)) @Valid @RequestBody DepositCommand depositCommand) {
        deposit.deposit(walletId, depositCommand.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Withdraw in wallet",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping(value = "/{walletId}/withdraw")
    public ResponseEntity<Void> withdraw(@Parameter(description = "wallet id to be obtained. Cannot be empty.", required = true) @Valid @NotEmpty @PathVariable UUID walletId,
                                         @Parameter(description="Withdraw command. Cannot null or empty.",
                                                 required=true, schema=@Schema(implementation = WithdrawCommand.class)) @Valid @RequestBody WithdrawCommand withdrawCommand) {
        withdraw.withDraw(walletId, withdrawCommand.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transfer",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping(value = "/transfer")
    public ResponseEntity<Void> transfer( @Parameter(description="Transfer command. Cannot null or empty.",
            required=true, schema=@Schema(implementation = TransferCommand.class)) @Valid @RequestBody TransferCommand transferCommand) {
        transferMoney.transfer(transferCommand.getWalletSrc(), transferCommand.getWalletTarget(), transferCommand.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get balance by wallet id", description = "Returns a single product", tags = { "wallets" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = BigDecimal.class))),

            @ApiResponse(responseCode = "404", description = "Wallet not found") })
    @GetMapping("/{walletId}")
    public ResponseEntity<BigDecimal> getBalance(@Parameter(description = "wallet id to be obtained. Cannot be empty.", required = true) @Valid @NotEmpty @PathVariable UUID walletId) {
        return new ResponseEntity<>( getBalance.getBalance(walletId), HttpStatus.OK);
    }

    @Operation(summary = "Get all wallets", description = "Get all wallets", tags = { "wallets" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = WalletResponse.class)))) })
    @GetMapping
    public ResponseEntity<List<WalletResponse>> getWallets() {
        return new ResponseEntity<>(getWallets.getWallets(), HttpStatus.OK);
    }
}
