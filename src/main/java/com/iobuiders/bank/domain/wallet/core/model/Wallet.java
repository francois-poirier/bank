package com.iobuiders.bank.domain.wallet.core.model;

import com.iobuiders.bank.domain.user.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet", schema = "bank")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Wallet {

    @Id
    @GeneratedValue
    private UUID id;

    @Min(0)
    @Column(name = "balance",nullable = false)
    @NotNull(message = "Wallet balance must be provided")
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Wallet(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
    }

    public void deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public boolean withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            return false;
        }
        this.balance = balance.subtract(amount);
        return true;
    }
}
