package com.vladhacksmile.searchjob.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import com.vladhacksmile.searchjob.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("vacancyGet")
@RequiredArgsConstructor
public class VacancyGetDelegate implements JavaDelegate {

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            User user = userService.authByToken(delegateExecution);
            Long id = (Long) delegateExecution.getVariable("vacancyId");
            Optional<Vacancy> vacancy = vacancyRepository.findById(id);
            ObjectMapper objectMapper = new ObjectMapper();
            if (vacancy.isPresent()) {
                delegateExecution.setVariable("result", objectMapper.writeValueAsString(vacancy.get()));
            }
        } catch (Throwable throwable) {
            throw new BpmnError("error", throwable.getMessage());
        }
    }
}
