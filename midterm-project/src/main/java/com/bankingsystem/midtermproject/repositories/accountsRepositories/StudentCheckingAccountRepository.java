package com.bankingsystem.midtermproject.repositories.accountsRepositories;

import com.bankingsystem.midtermproject.models.accounts.StudentCheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCheckingAccountRepository extends JpaRepository<StudentCheckingAccount, Long> {
}
