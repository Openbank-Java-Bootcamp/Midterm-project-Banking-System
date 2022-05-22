package com.bankingsystem.midtermproject.services.interfaces;


import com.bankingsystem.midtermproject.models.Role.Role;
import com.bankingsystem.midtermproject.models.users.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();

    void deleteUser(Long id);


}

