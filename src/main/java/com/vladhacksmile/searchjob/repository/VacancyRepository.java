package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Vacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findAllByNameContains(String string, Pageable pageable);
}
