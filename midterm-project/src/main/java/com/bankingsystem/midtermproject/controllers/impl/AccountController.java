package com.bankingsystem.midtermproject.controllers.impl;

import com.bankingsystem.midtermproject.DTO.AccountBalanceOnlyDTO;
import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CheckingAccount;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.AccountRepository;
import com.bankingsystem.midtermproject.services.interfaces.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AccountRepository accountRepository;





    //FIND LIST OF THE WHOLE ACCOUNTS

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll(){
            return accountRepository.findAll();
    }



    //FIND ACCOUNT BY ID

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable @Valid Long id){
        return accountService.getById(id);
    }



    //CREATE CHECKING ACCOUNT / STUDENT CHEKING ACCOUNT CREATION

    @PostMapping("/checkings")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCheckingAccount(@RequestBody newAccountDTO partialAccountDTO){
        return accountService.storeCheckingAccount(partialAccountDTO);
    }


    //TRANSFERS BETWEEN ACCOUNTS
    @PatchMapping("/transfers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@RequestParam Long idSenderAccount, @RequestParam Long idReceiverAccount, @RequestParam double amount){
        accountService.transfer(idSenderAccount, idReceiverAccount, amount);
    }

    //TRANSFER THIRD PARTY TO ACCOUNT
    @PatchMapping("/transfers/from-third-parties/{hashedKey}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferFromThirdPart(@PathVariable String hashedKey, @RequestParam Long idReceiverAccount, @RequestParam String receiverSecretKey, @RequestParam double amount){
        accountService.transferFromThirdParty(hashedKey, idReceiverAccount, receiverSecretKey, amount);
    }


    //TRANSFER ACCOUNT TO THIRD PARTY
    @PatchMapping("/transfers/to-third-parties/{hashedKey}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferToThirdPart(@PathVariable String hashedKey, @RequestParam Long idSenderAccount, @RequestParam String senderSecretKey, @RequestParam double amount){
        accountService.transferToThirdParty(hashedKey, idSenderAccount, senderSecretKey, amount);
    }

    //DELETE ACCOUNT
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }


    //MODIFICATION BALANCE BY ADMIN
    @PatchMapping("/balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void partialUpdateBalance(@PathVariable Long id, @RequestBody @Valid AccountBalanceOnlyDTO partialAccount) {
        accountService.updateBalance(id, partialAccount.getBalance());
    }








}
