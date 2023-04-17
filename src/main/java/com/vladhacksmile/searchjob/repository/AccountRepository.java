package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}