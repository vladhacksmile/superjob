package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

}
