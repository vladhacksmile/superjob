package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.dto.ResumeDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    VacancyRepository vacancyRepository;

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        if(resumeOptional.isPresent()) {
            return resumeOptional.get();
        }  else {
            return null;
        }
    }

    public void addResume(ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        resume.setSpecialization(resumeDTO.getSpecialization());
        resume.setDescription(resume.getDescription());
        resumeRepository.save(resume);
    }

    public void updateResume(ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        resume.setSpecialization(resumeDTO.getSpecialization());
        resume.setDescription(resume.getDescription());
        resumeRepository.save(resume);
    }

    public List<Vacancy> searchVacancy(SearchDTO searchDTO) {
        String name = searchDTO.getName();
        List<Vacancy>  vacancyList = (List<Vacancy>) vacancyRepository.findAll();
        List<Vacancy> vacancyResult = new LinkedList<>();

        for(Vacancy vacancy: vacancyList) {
            if (vacancy.getName().contains(name)) {
                vacancyResult.add(vacancy);
            }
            if (vacancy.getInformation().contains(name)) {
                vacancyResult.add(vacancy);
            }
        }
        return vacancyResult;
    }
}
