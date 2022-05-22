package com.bankingsystem.midtermproject.repositories.accountsRepositories;

import com.bankingsystem.midtermproject.models.accounts.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {
}
