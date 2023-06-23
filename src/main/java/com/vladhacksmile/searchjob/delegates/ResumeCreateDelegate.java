package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.resume.ResumeDTO;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.UserRepository;
import com.vladhacksmile.searchjob.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("resumeCreate")
@RequiredArgsConstructor
public class ResumeCreateDelegate implements JavaDelegate {

    final private ResumeService resumeService;

    final private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String username = (String) delegateExecution.getVariable("username");
        Optional<User> user = userRepository.findByMail(username);

        String specialization = (String) delegateExecution.getVariable("specialization");
        String description = (String) delegateExecution.getVariable("description");

        user.ifPresent(value -> resumeService.addResume(value, new ResumeDTO(1L, specialization, description)));
        System.out.println(delegateExecution.getCurrentActivityName());
    }
}
