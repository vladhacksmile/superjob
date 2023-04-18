package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.ResumeDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    ResponseRepository responseRepository;

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        if(resumeOptional.isPresent()) {
            return resumeOptional.get();
        }  else {
            return null;
        }
    }

    public Resume addResume(User user, ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        resume.setUser(user);
        resume.setSpecialization(resumeDTO.getSpecialization());
        resume.setDescription(resumeDTO.getDescription());
        resumeRepository.save(resume);
        return resume;
    }

    public boolean updateResume(User user, ResumeDTO resumeDTO) {
        Resume resume = getResumeById(resumeDTO.getResumeId());
        if(resume != null) {
            resume.setSpecialization(resumeDTO.getSpecialization());
            resume.setDescription(resume.getDescription());
            resumeRepository.save(resume);
            return true;
        }
        return false;
    }

    public boolean deleteResumeById(User user, long id) {
        Resume resume = getResumeById(id);
        if(resume != null) {
            resumeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Vacancy> searchVacancy(SearchDTO searchDTO) {
        Pageable pageable = PageRequest.of(searchDTO.getOffset() + 1, 10);
        return vacancyRepository.findAllByNameContains(searchDTO.getName(), pageable);
//        int fromIndex = (searchDTO.getOffset() - 1) * 10;
//        String name = searchDTO.getName();
//        List<Vacancy> vacancyList = vacancyRepository.findAll();
//        List<Vacancy> vacancyResult = new LinkedList<>();
//
//        for(Vacancy vacancy: vacancyList) {
//            if (vacancy.getName().contains(name)) {
//                vacancyResult.add(vacancy);
//            } else if (vacancy.getInformation().contains(name)) {
//                vacancyResult.add(vacancy);
//            }
//        }
//        List<Vacancy> result = new ArrayList<>();
//        try {
//            result = vacancyResult.subList(fromIndex, Math.min(fromIndex + 10, vacancyResult.size()));
//        } catch (IllegalArgumentException ignored) {}
//
//        return result;
    }

    public List<Response> reviewing(Long id) {
        Resume resume = getResumeById(id);
        return responseRepository.findAllByResume(resume);
    }
}
