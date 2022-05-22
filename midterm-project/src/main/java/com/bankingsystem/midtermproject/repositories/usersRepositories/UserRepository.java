package com.bankingsystem.midtermproject.repositories.usersRepositories;

import com.bankingsystem.midtermproject.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
