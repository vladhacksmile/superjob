package com.vladhacksmile.searchjob.repository;

import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findAllByVacancy(Vacancy vacancy);
    List<Response> findAllByResume(Resume resume);
    Response findByVacancyAndResume(Vacancy vacancy, Resume resume);
}