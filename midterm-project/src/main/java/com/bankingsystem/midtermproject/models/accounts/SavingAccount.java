package com.bankingsystem.midtermproject.models.accounts;

import com.bankingsystem.midtermproject.enums.Status;

import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.models.users.User;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.AccountRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import static com.bankingsystem.midtermproject.services.impl.AccountService.getCalendar;
import static java.util.Calendar.YEAR;


@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("saving_account")
@Table(name = "saving_account")
public class SavingAccount extends Account{


    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public SavingAccount(Money balance, User primaryOwner, User secondaryOwner, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, new Money(BigDecimal.valueOf(1000), Currency.getInstance("USD")), 0.0025, new Money(), secretKey);
        this.status = status;
        checkingCalendar(getBalance());
    }

    public void checkingCalendar(Money balance){
        Calendar today = getCalendar(new Date());
        Calendar creationDate = getCalendar(getCreationDate());
        int n = 1;
        if(today.get(Calendar.MONTH)-creationDate.get(Calendar.MONTH) == n){
            balance.setAmount(getBalance().getAmount().add(getBalance().getAmount().multiply(new BigDecimal(getInterestRate()).divide(BigDecimal.valueOf(12)))));
            setBalance(balance);
            n++;
        }
        setBalance(balance);
    }

}
