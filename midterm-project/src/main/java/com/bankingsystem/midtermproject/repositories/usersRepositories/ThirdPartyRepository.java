package com.bankingsystem.midtermproject.repositories.usersRepositories;

import com.bankingsystem.midtermproject.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {

    ThirdParty findByHashedKey(String hashedKey);
}
