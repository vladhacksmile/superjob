package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.ChangeStatusDTO;
import com.vladhacksmile.searchjob.dto.VacancyDTO;
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
public class VacancyService {
    final VacancyRepository vacancyRepository;

    final UserRepository userRepository;

    final ResumeRepository resumeRepository;

    final ResponseRepository responseRepository;

    public VacancyService(VacancyRepository vacancyRepository, UserRepository userRepository, ResumeRepository resumeRepository, ResponseRepository responseRepository) {
        this.vacancyRepository = vacancyRepository;
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.responseRepository = responseRepository;
    }

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

    public boolean addVacancy(VacancyDTO vacancyDTO) {
        Vacancy vacancy = new Vacancy();
        Optional<Account> optionalUser = userRepository.findById(vacancyDTO.getUserId());
        if(optionalUser.isPresent()) {
            vacancy.setAccount(optionalUser.get());
            vacancy.setName(vacancyDTO.getName());
            vacancy.setSalary(vacancyDTO.getSalary());
            vacancy.setInformation(vacancyDTO.getInformation());
            vacancyRepository.save(vacancy);
            return true;
        }
        return false;
    }

    public boolean deleteVacancyById(long id) {
        Vacancy vacancy = getVacancyById(id);
        if(vacancy != null) {
            vacancyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateVacancy(VacancyDTO vacancyDTO) {
        Vacancy vacancy = getVacancyById(vacancyDTO.getVacancyId());
        if(vacancy != null) {
            vacancy.setName(vacancyDTO.getName());
            vacancy.setSalary(vacancyDTO.getSalary());
            vacancy.setInformation(vacancyDTO.getInformation());
            vacancyRepository.save(vacancy);
            return true;
        }
        return false;
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
