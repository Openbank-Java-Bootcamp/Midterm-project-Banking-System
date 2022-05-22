package com.bankingsystem.midtermproject.repositories.roleRepository;

import com.bankingsystem.midtermproject.models.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
