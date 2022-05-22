package com.bankingsystem.midtermproject;

import com.bankingsystem.midtermproject.enums.Status;
import com.bankingsystem.midtermproject.models.Role.Role;
import com.bankingsystem.midtermproject.models.accounts.Account;
import com.bankingsystem.midtermproject.models.accounts.CheckingAccount;
import com.bankingsystem.midtermproject.models.money.Money;

import com.bankingsystem.midtermproject.models.users.User;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.AccountRepository;
import com.bankingsystem.midtermproject.services.impl.AccountService;
import com.bankingsystem.midtermproject.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
public class MidtermProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MidtermProjectApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, AccountService accountService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User("John Doe", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User("James Smith", "james", "1234", new ArrayList<>()));
			userService.saveUser(new User("Jane Carry", "jane", "1234", new ArrayList<>()));
			userService.saveUser(new User("Chris Anderson", "chris", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("james", "ROLE_ADMIN");
			userService.addRoleToUser("jane", "ROLE_USER");
			userService.addRoleToUser("chris", "ROLE_ADMIN");
			userService.addRoleToUser("chris", "ROLE_USER");


		};
	}

}

