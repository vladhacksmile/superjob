package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
