package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.vacancy.VacancyDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.enums.UserRole;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.security.exception.OperationNotPermitedException;
import com.vladhacksmile.searchjob.service.VacancyService;
import com.vladhacksmile.searchjob.service.auth.UserService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
@Named("vacancyCreate")
public class VacancyCreateDelegate implements JavaDelegate {

    @Autowired
    VacancyService vacancyService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        try {
            User user = userService.authByToken(delegateExecution);

            if (user.getRole() != UserRole.APPLICANT) throw new OperationNotPermitedException("Вы не работодатель");

            int salary = (Integer) delegateExecution.getVariable("salary");
            String name = (String) delegateExecution.getVariable("name");
            String information = (String) delegateExecution.getVariable("information");

            vacancyService.addVacancy(user, new VacancyDTO(null, salary, name, information));
            System.out.println(delegateExecution.getCurrentActivityName());
        } catch (Exception e) {
            throw new BpmnError("error", e.getMessage());
        }
    }
}
