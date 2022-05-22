package com.bankingsystem.midtermproject.DTO;

import com.bankingsystem.midtermproject.models.money.Money;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class AccountBalanceOnlyDTO {

    private Money balance;

    public AccountBalanceOnlyDTO() {
    }

    public AccountBalanceOnlyDTO(Money balance) {
        this.balance = balance;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
