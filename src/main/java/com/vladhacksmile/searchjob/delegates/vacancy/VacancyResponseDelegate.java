 package com.vladhacksmile.searchjob.delegates.vacancy;

import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.enums.ResumeStatus;
import com.vladhacksmile.searchjob.enums.UserRole;
import com.vladhacksmile.searchjob.repository.ResponseRepository;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import com.vladhacksmile.searchjob.security.exception.OperationNotPermitedException;
import com.vladhacksmile.searchjob.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

 @Component
 @Named("vacancyResponse")
 @RequiredArgsConstructor
 public class VacancyResponseDelegate implements JavaDelegate {
     @Autowired
     VacancyRepository vacancyRepository;

     @Autowired
     UserService userService;

     @Autowired
     TransactionTemplate transactionTemplate;

     @Autowired
     ResponseRepository responseRepository;

     @Autowired
     ResumeRepository resumeRepository;

     @Override
     public void execute(DelegateExecution delegateExecution) {
         try {
             User user = userService.authByToken(delegateExecution);

             if (user.getRole() != UserRole.APPLICANT) throw new OperationNotPermitedException("Вы не соискатель");

             long resumeId = Long.parseLong(delegateExecution.getVariable("resumeId").toString());
             long vacancyId = Long.parseLong(delegateExecution.getVariable("vacancyId").toString());
             transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

             transactionTemplate.execute(status -> {
                     Vacancy vacancy = getVacancyById(vacancyId);
                     if(vacancy == null) {
                         delegateExecution.setVariable("result", "Вакансия не найдена");
                         return false;
                     }

                     Resume resume = getResumeById(resumeId);
                     if (resume == null) {
                         delegateExecution.setVariable("result", "Резюме не найдено");
                         return false;
                     }

                     if (responseRepository.findByVacancyAndResume(vacancy, resume) != null) {
                         delegateExecution.setVariable("result", "Уже есть отклик с этим резюме");
                         return false;
                     }

                     if (Objects.equals(user.getId(), resume.getUser().getId())) {
                         Response response = new Response(resume, vacancy, ResumeStatus.REVIEW);
                         responseRepository.save(response);
                         delegateExecution.setVariable("result", "Отклик успешно отправлен");
                         return true;
                     }

                     delegateExecution.setVariable("result", "Нет прав");
                     return false;
                }
             );

         } catch (Throwable throwable) {
             throw new BpmnError("error", throwable.getMessage());
         }
     }

     public Vacancy getVacancyById(long id) {
         Optional<Vacancy> vacancyOptional = vacancyRepository.findById(id);
         return vacancyOptional.orElse(null);
     }

     public Resume getResumeById(long id) {
         Optional<Resume> resumeOptional = resumeRepository.findById(id);
         return resumeOptional.orElse(null);
     }
 }
