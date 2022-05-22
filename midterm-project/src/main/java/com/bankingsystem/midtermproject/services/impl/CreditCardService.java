package com.bankingsystem.midtermproject.services.impl;

import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CheckingAccount;
import com.bankingsystem.midtermproject.models.accounts.CreditCardAccount;
import com.bankingsystem.midtermproject.models.accounts.StudentCheckingAccount;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.CreditCardAccountRepository;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.SavingAccountRepository;
import com.bankingsystem.midtermproject.repositories.usersRepositories.UserRepository;
import com.bankingsystem.midtermproject.services.interfaces.ICreditCardService;
import com.bankingsystem.midtermproject.services.interfaces.ISavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.YEAR;

@Service
public class CreditCardService implements ICreditCardService {


    @Autowired
    private CreditCardAccountRepository creditCardAccountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account storeCreditCard(newAccountDTO partialAccountDTO) {
        CreditCardAccount creditCardAccount = new CreditCardAccount(partialAccountDTO.getBalance(), userRepository.findById(partialAccountDTO.getPrimaryOwnerId()).get(), userRepository.findById(partialAccountDTO.getSecondaryOwnerId()).get());
        return creditCardAccountRepository.save(creditCardAccount);
    }
}
