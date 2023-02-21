package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.dto.ResumeDTO;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

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
}
