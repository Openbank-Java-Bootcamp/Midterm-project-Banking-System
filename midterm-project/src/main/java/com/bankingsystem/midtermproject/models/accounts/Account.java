package com.bankingsystem.midtermproject.models.accounts;

import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.models.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static com.bankingsystem.midtermproject.services.impl.AccountService.getCalendar;


@Entity
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name= "account")
//@DiscriminatorColumn(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    private Money balance;

//    @NotEmpty(message = "Primary owner cannot be empty")
    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    private User primaryOwner;


    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private User secondaryOwner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency"))
    })
    private Money penaltyFee = new Money (new BigDecimal("40"));

    @Column(name= "creation_date")
    private Date creationDate = new Date();
        @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;

    @Column(name = "interest_rate")
    private double interestRate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit;


    @Column(name = "secret_key")
    private String secretKey;

    public Account(Money balance, User primaryOwner, User secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account(Money balance, User primaryOwner, User secondaryOwner, Money minimumBalance, double interestRate, Money creditLimit, String secretKey) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.secretKey = secretKey;
    }

    public void setBalance(Money balance) {
        if (minimumBalance == null) {
            this.balance = balance;
        } else {
            int rest = balance.getAmount().compareTo(minimumBalance.getAmount());
            if(rest == 0 || rest == 1) {
                this.balance = balance;
            } else {
                balance.setAmount(balance.getAmount().subtract(getPenaltyFee().getAmount()));
                this.balance = balance;
            }
      }
    }
}
