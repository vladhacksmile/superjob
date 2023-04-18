package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMail(String mail);

    Boolean existsByMail(String mail);

}