package com.bankingsystem.midtermproject.models.accounts;

import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.models.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import static com.bankingsystem.midtermproject.services.impl.AccountService.getCalendar;
import static java.util.Calendar.YEAR;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("credit_card")
public class CreditCardAccount extends Account{


    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit = new Money (new BigDecimal(100));


    public CreditCardAccount(Money balance, User primaryOwner, User secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner, new Money(BigDecimal.valueOf(0), Currency.getInstance("USD")), 0.2, new Money(BigDecimal.valueOf(100), Currency.getInstance("USD")), null);
        checkingCalendar(getBalance());
    }

    public void checkingCalendar(Money balance){
        Calendar today = getCalendar(new Date());
        Calendar creationDate = getCalendar(getCreationDate());
        if(today.get(YEAR)-creationDate.get(YEAR) == 1){
            balance.setAmount(getBalance().getAmount().add(getBalance().getAmount().multiply(new BigDecimal(getInterestRate()))));
            setBalance(balance);
        }
        setBalance(balance);
    }
}
