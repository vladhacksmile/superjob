package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.ResumeDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.Account;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.ResponseRepository;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    ResponseRepository responseRepository;;

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        if(resumeOptional.isPresent()) {
            return resumeOptional.get();
        }  else {
            return null;
        }
    }

    public boolean addResume(ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        Optional<Account> optionalUser = userRepository.findById(resumeDTO.getUserId());
        if(optionalUser.isPresent()) {
            resume.setAccount(optionalUser.get());
        } else {
            return false;
        }
        resume.setSpecialization(resumeDTO.getSpecialization());
        resume.setDescription(resumeDTO.getDescription());
        resumeRepository.save(resume);
        return true;
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
            } else if (vacancy.getInformation().contains(name)) {
                vacancyResult.add(vacancy);
            }
        }
        return vacancyResult;
    }

    public List<Response> reviewing(Long id) {
        Resume resume = getResumeById(id);
        List<Response> responses = responseRepository.findAllByResume(resume);
        return responses;
    }
}
