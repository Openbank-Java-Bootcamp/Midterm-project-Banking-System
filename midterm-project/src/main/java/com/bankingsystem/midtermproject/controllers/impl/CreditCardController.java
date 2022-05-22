package com.bankingsystem.midtermproject.controllers.impl;

import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CreditCardAccount;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.CreditCardAccountRepository;
import com.bankingsystem.midtermproject.services.interfaces.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class CreditCardController {

    @Autowired
    private ICreditCardService creditCardService;

    @Autowired
    private CreditCardAccountRepository creditCardAccountRepository;


    //CREATE CREDITCARD ACCOUNT

    @PostMapping("/creditcards")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCreditCardAccount(@RequestBody newAccountDTO patialCreditCardAccount){
        return creditCardService.storeCreditCard(patialCreditCardAccount);
    }
}
