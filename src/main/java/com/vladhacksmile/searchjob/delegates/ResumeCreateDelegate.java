package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.resume.ResumeDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.service.ResumeService;
import com.vladhacksmile.searchjob.service.auth.UserService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
@Named("resumeCreate")
public class ResumeCreateDelegate implements JavaDelegate {

    @Autowired
    ResumeService resumeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        try {
            User user = userService.authByToken(delegateExecution);
            System.out.println(user.getMail());
            String specialization = (String) delegateExecution.getVariable("specialization");
            String description = (String) delegateExecution.getVariable("description");
            System.out.println(specialization);
            resumeService.addResume(user, new ResumeDTO(null, specialization, description));
            System.out.println(delegateExecution.getCurrentActivityName());
        } catch (Exception e) {
            throw new BpmnError("resume_error", e.getMessage());
        }
    }
}
