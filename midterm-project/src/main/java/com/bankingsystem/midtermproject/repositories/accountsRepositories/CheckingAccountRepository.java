package com.bankingsystem.midtermproject.repositories.accountsRepositories;

import com.bankingsystem.midtermproject.models.accounts.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
}
