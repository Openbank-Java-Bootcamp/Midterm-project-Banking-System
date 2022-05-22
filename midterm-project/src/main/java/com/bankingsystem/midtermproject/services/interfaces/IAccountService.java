package com.bankingsystem.midtermproject.services.interfaces;

import com.bankingsystem.midtermproject.DTO.AccountBalanceOnlyDTO;
import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CheckingAccount;
import com.bankingsystem.midtermproject.models.money.Money;

public interface IAccountService {
    Account storeCheckingAccount(newAccountDTO partialAccountDTO);

    void transfer(Long idSenderAccount, Long idReceiverAccount, double amount);

    void deleteAccount(Long id);

    void updateBalance(Long id, Money balance);

    void transferFromThirdParty(String hashedKey, Long idReceiverAccount, String receiverSecretKey, double amount);

    void transferToThirdParty(String hashedKey, Long idSenderAccount, String senderSecretKey, double amount);

    Account getById(Long id);
}
