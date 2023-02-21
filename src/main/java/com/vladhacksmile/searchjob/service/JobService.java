package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.dto.ResponseVacancyDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.UserRepository;
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
    UserRepository userRepository;

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

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        if(resumeOptional.isPresent()) {
            return resumeOptional.get();
        }  else {
            return null;
        }
    }

    public boolean addVacancy(JobDTO jobDTO) {
        Vacancy vacancy = new Vacancy();
        Optional<User> optionalUser = userRepository.findById(jobDTO.getUserId());
        if(optionalUser.isPresent()) {
            vacancy.setUser(optionalUser.get());
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
        if(vacancy == null) {
            return false;
        } else {
            vacancy.getResume().add(getResumeById(responseVacancyDTO.getResumeId()));
            vacancyRepository.save(vacancy);
            return true;
        }
    }

    public List<Resume> searchResume(SearchDTO searchDTO) {
        String name = searchDTO.getName();
        List<Resume>  resumeList = (List<Resume>) resumeRepository.findAll();
        List<Resume> resumesResult = new LinkedList<>();

        for(Resume resume: resumeList) {
            if (resume.getSpecialization().contains(name)) {
                resumesResult.add(resume);
            } else if (resume.getDescription().contains(name)) {
                resumesResult.add(resume);
            }
        }
        return resumesResult;
    }

    public List<Resume> reviewing(Long id) {
        Vacancy vacancy = getVacancyById(id);
        List<Resume> resumeList = new LinkedList<>();
        if (vacancy != null) {
           resumeList = (List<Resume>) vacancy.getResume();
        }
        return resumeList;
    }
}
