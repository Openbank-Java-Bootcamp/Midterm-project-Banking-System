package com.bankingsystem.midtermproject.services.impl;

import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CreditCardAccount;
import com.bankingsystem.midtermproject.models.accounts.SavingAccount;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.SavingAccountRepository;
import com.bankingsystem.midtermproject.repositories.usersRepositories.UserRepository;
import com.bankingsystem.midtermproject.services.interfaces.ISavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingAccountService implements ISavingAccountService {

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account storeSavingAccount(newAccountDTO partialAccountDTO) {
        SavingAccount savingAccount = new SavingAccount(partialAccountDTO.getBalance(), userRepository.findById(partialAccountDTO.getPrimaryOwnerId()).get(), userRepository.findById(partialAccountDTO.getSecondaryOwnerId()).get(), partialAccountDTO.getSecretKey(), Status.ACTIVE);
        return savingAccountRepository.save(savingAccount);
    }


}
