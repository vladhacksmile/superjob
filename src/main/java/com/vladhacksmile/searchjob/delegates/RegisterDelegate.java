package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.MessageResponse;
import com.vladhacksmile.searchjob.dto.auth.RegisterRequest;
import com.vladhacksmile.searchjob.enums.UserRole;
import com.vladhacksmile.searchjob.service.ResumeService;
import com.vladhacksmile.searchjob.service.auth.UserService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
@Named("register")
public class RegisterDelegate implements JavaDelegate {

    @Autowired
    UserService userService;
    @Autowired
    ResumeService resumeService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            MessageResponse messageResponse = userService.register(new RegisterRequest((delegateExecution.getVariable("role")).equals("EMPLOYER") ? UserRole.EMPLOYER : UserRole.APPLICANT, (String) delegateExecution.getVariable("name"),
                    (String) delegateExecution.getVariable("surname"), (String) delegateExecution.getVariable("patronymic"),
                            Integer.parseInt((String) delegateExecution.getVariable("age")), (String) delegateExecution.getVariable("number"), (String) delegateExecution.getVariable("username"), (String) delegateExecution.getVariable("password")));

            delegateExecution.setVariable("result", messageResponse.getMessage());
            System.out.println(delegateExecution.getCurrentActivityName());
        } catch (Throwable throwable) {
            delegateExecution.setVariable("register_error", throwable.getMessage());
            throwable.printStackTrace();
            throw new BpmnError("register_error", throwable.getMessage());
        }
    }
}
