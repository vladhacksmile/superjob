package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.resume.ResumeDTO;
import com.vladhacksmile.searchjob.dto.vacancy.VacancyDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.service.ResumeService;
import com.vladhacksmile.searchjob.service.VacancyService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("resumeCreate")
public class ResumeCreateDelegate implements JavaDelegate {

    @Autowired
    ResumeService resumeService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String username = (String) delegateExecution.getVariable("username");
        Optional<User> user = userRepository.findByMail(username);

        String specialization = (String) delegateExecution.getVariable("specialization");
        String description = (String) delegateExecution.getVariable("description");

        user.ifPresent(value -> resumeService.addResume(value, new ResumeDTO(1l, specialization, description)));
        System.out.println(delegateExecution.getCurrentActivityName());
    }
}
