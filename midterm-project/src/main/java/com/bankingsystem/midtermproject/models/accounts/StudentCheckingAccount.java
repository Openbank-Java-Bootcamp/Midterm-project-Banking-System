package com.bankingsystem.midtermproject.models.accounts;


import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.models.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue("student_checking_account")
@Table(name = "student_checking_account")
public class StudentCheckingAccount extends Account{

    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentCheckingAccount(Money balance, User primaryOwner, User secondaryOwner, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, new Money(), 0, new Money(), secretKey);
        this.status = status;
    }
}
