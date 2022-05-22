package com.bankingsystem.midtermproject.models.accounts;


import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.models.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;


@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("checking_account")
@Table(name = "checking_account")
public class CheckingAccount extends Account {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    private Money monthlyMaintenanceFee = new Money(new BigDecimal(12));

    @Enumerated(EnumType.STRING)
    private Status status;

    public CheckingAccount(Money balance, User primaryOwner, User secondaryOwner, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, new Money(BigDecimal.valueOf(250), Currency.getInstance("USD")), 0.00, new Money(), secretKey);
        this.status = status;
    }


}
