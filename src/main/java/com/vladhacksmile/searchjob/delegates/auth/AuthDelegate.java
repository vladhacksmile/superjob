package com.vladhacksmile.searchjob.delegates.auth;

import com.vladhacksmile.searchjob.service.ResumeService;
import com.vladhacksmile.searchjob.service.auth.UserService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
@Named("auth")
public class AuthDelegate implements JavaDelegate {

    @Autowired
    UserService userService;
    @Autowired
    ResumeService resumeService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            userService.auth(delegateExecution);
            System.out.println(delegateExecution.getCurrentActivityName());
        } catch (Throwable throwable) {
            throw new BpmnError("error", throwable.getMessage());
        }
    }
}