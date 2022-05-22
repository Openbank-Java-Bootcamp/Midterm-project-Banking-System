package com.bankingsystem.midtermproject.services.interfaces;

import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.models.accounts.Account;

public interface ISavingAccountService {

    Account storeSavingAccount(newAccountDTO partialAccountDTO);


}
