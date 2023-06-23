package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.vacancy.VacancyDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("vacancyCreate")
@RequiredArgsConstructor
public class VacancyCreateDelegate implements JavaDelegate {

    final private VacancyService vacancyService;

    final private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        int salary = (Integer) delegateExecution.getVariable("salary");
        String name = (String) delegateExecution.getVariable("name");
        String information = (String) delegateExecution.getVariable("information");
        String username = (String) delegateExecution.getVariable("username");

        Optional<User> user = userRepository.findByMail(username);

        user.ifPresent(value -> vacancyService.addVacancy(value, new VacancyDTO(1L, salary, name, information)));
        System.out.println(delegateExecution.getCurrentActivityName());
    }
}
