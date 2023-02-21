package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
    boolean existsByMail(String mail);
}