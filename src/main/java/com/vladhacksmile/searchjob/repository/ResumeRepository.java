package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
