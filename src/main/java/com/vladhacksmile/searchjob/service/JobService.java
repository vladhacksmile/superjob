package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {
    @Autowired
    VacancyRepository vacancyRepository;

    public Vacancy getVacancyById(long id) {
        Optional<Vacancy> vacancyOptional = vacancyRepository.findById(id);
        if(vacancyOptional.isPresent()) {
            return vacancyOptional.get();
        }  else {
            return null;
        }
    }

    public void addVacancy(JobDTO jobDTO) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(jobDTO.getName());
        vacancy.setSalary(jobDTO.getSalary());
        vacancy.setInformation(jobDTO.getInformation());
        vacancyRepository.save(vacancy);
    }

    public void updateVacancy(JobDTO jobDTO) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(jobDTO.getName());
        vacancy.setSalary(jobDTO.getSalary());
        vacancy.setInformation(jobDTO.getInformation());
        vacancyRepository.save(vacancy);
    }
}
