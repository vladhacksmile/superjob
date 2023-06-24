package com.vladhacksmile.searchjob.delegates.resume;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
@Named("resumeSearch")
@RequiredArgsConstructor
public class ResumeSearchDelegate implements JavaDelegate {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            User user = userService.authByToken(delegateExecution);
            String name = (String) delegateExecution.getVariable("name");
            int offset = Integer.parseInt(delegateExecution.getVariable("offset").toString());
            Pageable pageable = PageRequest.of(offset - 1, 10);
            ObjectMapper objectMapper = new ObjectMapper();
            delegateExecution.setVariable("result", objectMapper.writeValueAsString(resumeRepository.findAllBySpecializationContains(name, pageable)));
        } catch (Throwable throwable) {
            throw new BpmnError("error", throwable.getMessage());
        }
    }
}
