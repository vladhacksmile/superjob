package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
