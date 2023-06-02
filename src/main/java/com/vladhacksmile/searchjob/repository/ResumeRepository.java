package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Resume;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findAllBySpecializationContains(String string, Pageable pageable);
}
