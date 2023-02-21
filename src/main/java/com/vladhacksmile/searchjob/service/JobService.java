package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    private ResumeRepository resumeRepository;

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

    public List<Resume> searchResume(SearchDTO searchDTO) {
        String name = searchDTO.getName();
        List<Resume>  resumeList = (List<Resume>) resumeRepository.findAll();
        List<Resume> resumesResult = new LinkedList<>();

        for(Resume resume: resumeList) {
            if (resume.getSpecialization().contains(name)) {
                resumesResult.add(resume);
            }
            if (resume.getDescription().contains(name)) {
                resumesResult.add(resume);
            }
        }
        return resumesResult;
    }
}
