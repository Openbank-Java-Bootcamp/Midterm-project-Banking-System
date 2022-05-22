package com.bankingsystem.midtermproject.controllers.impl;

import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.SavingAccount;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.SavingAccountRepository;
import com.bankingsystem.midtermproject.services.interfaces.ISavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class SavingAccountController {

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    private ISavingAccountService savingAccountService;


    // CREATE SAVING ACCOUNT

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addSavingAccount(@RequestBody newAccountDTO patialSavingAccount){
        return savingAccountService.storeSavingAccount(patialSavingAccount);
    }
}
