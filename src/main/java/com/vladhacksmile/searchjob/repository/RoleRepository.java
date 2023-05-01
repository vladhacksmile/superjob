package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Role;
import com.vladhacksmile.searchjob.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRole userRole);
}
