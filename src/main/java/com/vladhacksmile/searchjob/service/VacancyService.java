package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.dto.resume.ChangeStatusDTO;
import com.vladhacksmile.searchjob.dto.vacancy.ResponseVacancyDTO;
import com.vladhacksmile.searchjob.dto.vacancy.VacancyDTO;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.enums.ResumeStatus;
import com.vladhacksmile.searchjob.repository.ResponseRepository;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    TransactionTemplate transactionTemplate;

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

    public Vacancy addVacancy(User user, VacancyDTO vacancyDTO) {
        Vacancy vacancy = new Vacancy();
        vacancy.setUser(user);
        vacancy.setName(vacancyDTO.getName());
        vacancy.setSalary(vacancyDTO.getSalary());
        vacancy.setInformation(vacancyDTO.getInformation());
        vacancyRepository.save(vacancy);
        return vacancy;
    }

    public boolean deleteVacancyById(User user, long id) {
        Vacancy vacancy = getVacancyById(id);
        if(vacancy != null) {
            vacancyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateVacancy(User user, VacancyDTO vacancyDTO) {
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
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        return Boolean.TRUE.equals(transactionTemplate.execute(
                status -> {
                    Vacancy vacancy = getVacancyById(responseVacancyDTO.getVacancyId());
                    Resume resume = getResumeById(responseVacancyDTO.getResumeId());
                    if(vacancy == null || resume == null || responseRepository.findByVacancyAndResume(vacancy, resume) != null) {
                        return false;
                    } else {
                        Response response = new Response(resume, vacancy, ResumeStatus.REVIEW);
                        responseRepository.save(response);
                        return true;
                    }
                }
        ));
    }

    public List<Resume> searchResume(SearchDTO searchDTO) {
        Pageable pageable = PageRequest.of(searchDTO.getOffset() - 1, 10);
        return resumeRepository.findAllBySpecializationContains(searchDTO.getName(), pageable);
    }

    public List<Response> reviewing(Long id) {
        Vacancy vacancy = getVacancyById(id);
        return responseRepository.findAllByVacancy(vacancy);
    }

//    public boolean changeStatus(ChangeStatusDTO changeStatusDTO) {
//        try {
//            userTransaction.begin();
//            Resume resume = getResumeById(changeStatusDTO.getResumeId());
//            Vacancy vacancy = getVacancyById(changeStatusDTO.getVacancyId());
//            if (resume != null && vacancy != null) {
//                Response response = responseRepository.findByVacancyAndResume(vacancy, resume);
//                response.setResumeStatus(changeStatusDTO.getResumeStatus());
//                responseRepository.save(response);
//                userTransaction.commit();
//                return true;
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            try {
//                userTransaction.rollback();
//            } catch (Exception ex) {
//                System.err.println(ex.getMessage());
//            }
//        }
//        return false;
//    }

    public boolean changeStatus(ChangeStatusDTO changeStatusDTO) {
        return Boolean.TRUE.equals(transactionTemplate.execute(
                status -> {
                    Resume resume = getResumeById(changeStatusDTO.getResumeId());
                    Vacancy vacancy = getVacancyById(changeStatusDTO.getVacancyId());
                    if (resume != null && vacancy != null) {
                        Response response = responseRepository.findByVacancyAndResume(vacancy, resume);
                        response.setResumeStatus(changeStatusDTO.getResumeStatus());
                        responseRepository.save(response);
                        return true;

                    }
                    return false;
                }
        ));
    }
}
