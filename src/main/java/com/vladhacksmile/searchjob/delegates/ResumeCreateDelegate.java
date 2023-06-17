package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.resume.ResumeDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.enums.UserRole;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.security.exception.OperationNotPermitedException;
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

            if (user.getRole() != UserRole.APPLICANT) throw new OperationNotPermitedException("Вы не соискатель");

            String specialization = (String) delegateExecution.getVariable("specialization");
            String description = (String) delegateExecution.getVariable("description");
            resumeService.addResume(user, new ResumeDTO(null, specialization, description));
            System.out.println(delegateExecution.getCurrentActivityName());
            delegateExecution.setVariable("result", "Резюме успешно добавлено");
        } catch (Throwable throwable) {
            throw new BpmnError("error", throwable.getMessage());
        }
    }
}
