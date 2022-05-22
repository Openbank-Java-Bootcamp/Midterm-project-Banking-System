package com.bankingsystem.midtermproject.repositories.accountsRepositories;

import com.bankingsystem.midtermproject.models.accounts.CreditCardAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardAccountRepository extends JpaRepository<CreditCardAccount, Long> {
}
