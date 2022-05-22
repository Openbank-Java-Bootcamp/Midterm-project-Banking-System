package com.bankingsystem.midtermproject.controllers.impl;

import com.bankingsystem.midtermproject.models.users.ThirdParty;
import com.bankingsystem.midtermproject.models.users.User;
import com.bankingsystem.midtermproject.repositories.accountsRepositories.SavingAccountRepository;
import com.bankingsystem.midtermproject.repositories.usersRepositories.ThirdPartyRepository;
import com.bankingsystem.midtermproject.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    //GET USER BY USERNAME

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@PathVariable String username){
       return userService.getUser(username);
    }



    //CREATE USER

    @PostMapping("/account-holders")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }




    //CREATE THIRD PARTY

    @PostMapping("/third-parties")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty saveThirdParty(@RequestBody ThirdParty thirdParty) {
        thirdParty.setHashedKey(passwordEncoder.encode(thirdParty.getHashedKey()));
        return thirdPartyRepository.save(thirdParty);
    }



    //DELETE USER

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
