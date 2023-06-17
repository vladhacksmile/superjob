package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.dto.auth.AuthRequest;
import com.vladhacksmile.searchjob.dto.auth.JwtResponse;
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
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            String username = (String) delegateExecution.getVariable("username");
            String password = (String) delegateExecution.getVariable("password");
            JwtResponse jwtResponse = userService.login(new AuthRequest(username, password));

            String accessToken = jwtResponse.getToken();
            String refreshToken = jwtResponse.getRefreshToken();

            delegateExecution.setVariable("accessToken", accessToken);
            delegateExecution.setVariable("refreshToken", refreshToken);

            System.out.println(delegateExecution.getCurrentActivityName());
        } catch (Throwable throwable) {
            throw new BpmnError("auth_error", throwable.getMessage());
        }
    }
}
