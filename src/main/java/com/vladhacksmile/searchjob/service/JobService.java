package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.ChangeStatusDTO;
import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.dto.ResponseVacancyDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.Account;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.enums.ResumeStatus;
import com.vladhacksmile.searchjob.repository.ResponseRepository;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {
    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    ResponseRepository responseRepository;

    public Vacancy getVacancyById(long id) {
        Optional<Vacancy> vacancyOptional = vacancyRepository.findById(id);
        if(vacancyOptional.isPresent()) {
            return vacancyOptional.get();
        } else {
            return null;
        }
    }

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        if(resumeOptional.isPresent()) {
            return resumeOptional.get();
        } else {
            return null;
        }
    }

    public boolean addVacancy(JobDTO jobDTO) {
        Vacancy vacancy = new Vacancy();
        Optional<Account> optionalUser = userRepository.findById(jobDTO.getUserId());
        if(optionalUser.isPresent()) {
            vacancy.setAccount(optionalUser.get());
        } else {
            return false;
        }
        vacancy.setName(jobDTO.getName());
        vacancy.setSalary(jobDTO.getSalary());
        vacancy.setInformation(jobDTO.getInformation());
        vacancyRepository.save(vacancy);
        return true;
    }

    public void updateVacancy(JobDTO jobDTO) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(jobDTO.getName());
        vacancy.setSalary(jobDTO.getSalary());
        vacancy.setInformation(jobDTO.getInformation());
        vacancyRepository.save(vacancy);
    }

    public boolean responseVacancy(ResponseVacancyDTO responseVacancyDTO) {
        Vacancy vacancy = getVacancyById(responseVacancyDTO.getVacancyId());
        Resume resume = getResumeById(responseVacancyDTO.getResumeId());
        if(vacancy == null || resume == null) {
            return false;
        } else {
            Response response = new Response(resume, vacancy, ResumeStatus.REVIEW);
            responseRepository.save(response);
            return true;
        }
    }

    public Set<Resume> searchResume(SearchDTO searchDTO) {
        String name = searchDTO.getName();
        List<Resume> resumeList = resumeRepository.findAll();
        Set<Resume> resumesResult = new HashSet<>();

        for(Resume resume: resumeList) {
            if (resume.getSpecialization().contains(name)) {
                resumesResult.add(resume);
            } else if (resume.getDescription().contains(name)) {
                resumesResult.add(resume);
            }
        }
        return resumesResult;
    }

    public List<Response> reviewing(Long id) {
        Vacancy vacancy = getVacancyById(id);
        return responseRepository.findAllByVacancy(vacancy);
    }

    public boolean changeStatus(ChangeStatusDTO changeStatusDTO) {
        Resume resume = getResumeById(changeStatusDTO.getResumeId());
        Vacancy vacancy = getVacancyById(changeStatusDTO.getVacancyId());
        if(resume != null && vacancy != null) {
            Response response = responseRepository.findByVacancyAndResume(vacancy, resume);
            response.setResumeStatus(changeStatusDTO.getResumeStatus());
            responseRepository.save(response);
            return true;
        } else {
            return false;
        }
    }
}
