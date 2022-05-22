package com.bankingsystem.midtermproject.controllers.impl;

import com.bankingsystem.midtermproject.models.Role.Role;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CheckingAccount;
import com.bankingsystem.midtermproject.models.accounts.SavingAccount;
import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.users.User;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.AccountRepository;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.SavingAccountRepository;
import com.bankingsystem.midtermproject.models.users.address.Address;
import com.bankingsystem.midtermproject.repositories.usersRepositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    User accountHolder1;
    User accountHolder2;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder1 = new User("Ana","anni", "1234" , new ArrayList<>(), new Date(2000-05-27), new Address("Calle Plazuela", "Madrid", "28231"), new Address("Calle Plazuela", "Madrid", "28231"));
        accountHolder2 = new User("Adri", "adri", "1234", new ArrayList<>(), new Date(1994-03-24), new Address("Calle Plazuela", "Madrid", "28231"), new Address("Calle Plazuela", "Madrid", "28231"));
        userRepository.saveAll(List.of(accountHolder1, accountHolder2));

        Account account1 = new Account(new Money(new BigDecimal(250)), accountHolder1, accountHolder2);
        Account account2 = new Account(new Money(new BigDecimal(250)), accountHolder1, accountHolder2);
        accountRepository.saveAll(List.of(account1, account2));

        SavingAccount savingAccount1 = new SavingAccount(new Money(new BigDecimal(250)), accountHolder1, accountHolder2, "1234", Status.ACTIVE);
        SavingAccount savingAccount2 = new SavingAccount(new Money(new BigDecimal(250)), accountHolder1, accountHolder2, "1234", Status.FROZEN);
        savingAccountRepository.saveAll(List.of(savingAccount1, savingAccount2));
    }




    @AfterEach
    void tearDown() {
//        accountRepository.deleteAll();
//        savingAccountRepository.deleteAll();
//        accountHolderRepository.deleteAll();

    }

    @Test
    void searchAll_Valid_Works() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andReturn();
    }

    @Test
    void searchById_Valid_Works() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/accounts/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ana"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Adri"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Juan"));

    }

    @Test
    void addcheckingAccount_Valid_Works() throws Exception{
        String body = objectMapper.writeValueAsString(new CheckingAccount(new Money(new BigDecimal(100)), accountHolder1, accountHolder2, "1234", Status.ACTIVE));
        MvcResult result = mockMvc.perform(post("/checking-accounts").content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Calle Plazuela"));
    }





}