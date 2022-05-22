package com.bankingsystem.midtermproject.controllers.impl;

import com.bankingsystem.midtermproject.models.accounts.SavingAccount;
import com.bankingsystem.midtermproject.models.money.Money;
import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.users.User;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class SavingAccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SavingAccountRepository savingAccountRepository;

    User accountHolder1;
    User accountHolder2;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder1 = new User("Ana","anni", "1234" , new ArrayList<>(), new Date(2000-05-27), new Address("Calle Plazuela", "Madrid", "28231"), new Address("Calle Plazuela", "Madrid", "28231"));
        accountHolder2 = new User("Adri", "adri", "1234", new ArrayList<>(), new Date(1994-03-24), new Address("Calle Plazuela", "Madrid", "28231"), new Address("Calle Plazuela", "Madrid", "28231"));
        userRepository.saveAll(List.of(accountHolder1, accountHolder2));


//        SavingAccount savingAccount1 = new SavingAccount(new Money(new BigDecimal(250)), accountHolder1, accountHolder2, "1234", new Date(2020-10-15), Status.ACTIVE);
//        SavingAccount savingAccount2 = new SavingAccount(new Money(new BigDecimal(250)), accountHolder1, accountHolder2, "1234", new Date(2021-11-20), Status.FROZEN);
//        savingAccountRepository.saveAll(List.of(savingAccount1, savingAccount2));
    }

    @AfterEach
    void tearDown() {
        savingAccountRepository.deleteAll();
        userRepository.deleteAll();

    }


    @Test
    void addSavingAccount_Valid_Works() throws Exception{
        String body = objectMapper.writeValueAsString(new SavingAccount(new Money(new BigDecimal(250)), accountHolder1, accountHolder2, "1234", Status.ACTIVE));
        MvcResult result = mockMvc.perform(post("/savingaccounts").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1234"));
    }
}