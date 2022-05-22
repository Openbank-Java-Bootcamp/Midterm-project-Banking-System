package com.bankingsystem.midtermproject.services.impl;

import com.bankingsystem.midtermproject.DTO.newAccountDTO;
import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CheckingAccount;
import com.bankingsystem.midtermproject.models.accounts.StudentCheckingAccount;
import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.*;
import com.bankingsystem.midtermproject.repositories.usersRepositories.ThirdPartyRepository;
import com.bankingsystem.midtermproject.repositories.usersRepositories.UserRepository;
import com.bankingsystem.midtermproject.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Calendar.YEAR;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;


    @Autowired
    private StudentCheckingAccountRepository studentCheckingAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private UserRepository userRepository;


    //GET ACCOUNT BY OWNER

    public Account getById(Long id){
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            Account account = accountRepository.findById(id).get();
            if (account.getPrimaryOwner().getUsername().equals(username)  || account.getSecondaryOwner().getUsername().equals(username) || userRepository.findByUsername(username).getRoles().toString().contains("ROLE_ADMIN")){
                return account;
            }else{
                throw new IllegalArgumentException("You cannot access to this account");
            }
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This accounts does not exist.");
        }
    }

    //CREATE CHECKING ACCOUNT / STUDENT CHEKING ACCOUNT CREATION
    public Account storeCheckingAccount(newAccountDTO partialAccountDTO) {
        Calendar today = getCalendar(new Date());
        Calendar dateOfBirth = getCalendar(userRepository.findById(partialAccountDTO.getPrimaryOwnerId()).get().getDateOfBirth());
        if (today.get(YEAR) - dateOfBirth.get(YEAR) < 24) {
            StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount(partialAccountDTO.getBalance(), userRepository.findById(partialAccountDTO.getPrimaryOwnerId()).get(), userRepository.findById(partialAccountDTO.getSecondaryOwnerId()).get(), partialAccountDTO.getSecretKey(), Status.ACTIVE);
            return studentCheckingAccountRepository.save(studentCheckingAccount);
        }
        CheckingAccount checkingAccount = new CheckingAccount(partialAccountDTO.getBalance(), userRepository.findById(partialAccountDTO.getPrimaryOwnerId()).get(), userRepository.findById(partialAccountDTO.getSecondaryOwnerId()).get(), partialAccountDTO.getSecretKey(), Status.ACTIVE);
        return checkingAccountRepository.save(checkingAccount);
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }



    //TRANSFER FROM ACCOUNT TO ACCOUNT
    public void transfer(Long idSenderAccount, Long idReceiverAccount, double amount) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            Account accountSender = accountRepository.findById(idSenderAccount).get();
            Account accountReceiver = accountRepository.findById(idReceiverAccount).get();
            if (accountSender.getPrimaryOwner().getUsername().equals(username)  || accountSender.getSecondaryOwner().getUsername().equals(username)) {
                if (accountSender.getBalance().getAmount().compareTo(new BigDecimal(amount)) >= 0) {
                    accountSender.setBalance(new Money(accountSender.getBalance().getAmount().subtract(new BigDecimal(amount))));
                    accountReceiver.setBalance(new Money(accountReceiver.getBalance().getAmount().add(new BigDecimal(amount))));
                    accountRepository.save(accountSender);
                    accountRepository.save(accountReceiver);
                } else {
                    throw new IllegalArgumentException("Your balance is lower than the amount you try to send");
                }
            } else {
                throw new IllegalArgumentException("You cannot operate in this account");
            }
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One of this accounts does not exist.");

        }


    }

    //TRANSFER FROM THIRD PARTY TO ACCOUNT
    public void transferFromThirdParty(String hashedKey, Long idReceiverAccount, String receiverSecretKey, double amount) {
        try{
            Account accountReceiver = accountRepository.findById(idReceiverAccount).get();
            thirdPartyRepository.findByHashedKey(hashedKey);
            if (accountReceiver.getSecretKey().equals(receiverSecretKey)) {
                accountReceiver.setBalance(new Money(accountReceiver.getBalance().getAmount().add(new BigDecimal(amount))));
                accountRepository.save(accountReceiver);
            } else {
                throw new IllegalArgumentException("Invalid secret key");
            }
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid account or hashed key.");
        }
    }


    //TRANSFER FROM ACCOUNT TO THIRD PARTY
    public void transferToThirdParty(String hashedKey, Long idSenderAccount, String senderSecretKey, double amount) {
        try{
            Account accountSender = accountRepository.findById(idSenderAccount).get();
            thirdPartyRepository.findByHashedKey(hashedKey);
            if (accountSender.getSecretKey().equals(senderSecretKey)) {
                if (accountSender.getBalance().getAmount().compareTo(new BigDecimal(amount)) >= 0) {
                    accountSender.setBalance(new Money(accountSender.getBalance().getAmount().subtract(new BigDecimal(amount))));
                    accountRepository.save(accountSender);
                }else{
                    throw new IllegalArgumentException("Your balance is lower than the amount you try to send");
                }
            } else {
                throw new IllegalArgumentException("Invalid secret key");
            }
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid account or hashed key.");
        }
    }


        //DELETE ACCOUNT
        public void deleteAccount(Long id) {
        Optional<Account> foundAccount = accountRepository.findById(id);
        if(foundAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Account found with that ID");
        }
        accountRepository.delete(foundAccount.get());
    }


    public void updateBalance(Long id, Money balance) {
        Account accountFromDB = accountRepository.findById(id).get();
        accountFromDB.setBalance(balance);
        accountRepository.save(accountFromDB);
    }

}