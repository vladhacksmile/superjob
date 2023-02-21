package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
