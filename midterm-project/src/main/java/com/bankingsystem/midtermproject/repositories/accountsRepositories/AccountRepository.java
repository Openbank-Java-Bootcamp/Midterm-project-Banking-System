package com.bankingsystem.midtermproject.repositories.accountsRepositories;

import com.bankingsystem.midtermproject.models.accounts.Account;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAll();

}
