package com.vladhacksmile.searchjob.delegates.vacancy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.enums.UserRole;
import com.vladhacksmile.searchjob.repository.ResponseRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import com.vladhacksmile.searchjob.security.exception.OperationNotPermitedException;
import com.vladhacksmile.searchjob.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

@Component
@Named("vacancyReview")
@RequiredArgsConstructor
public class VacancyReviewDelegate implements JavaDelegate {

    @Autowired
    ResponseRepository responseRepository;
    @Autowired
    VacancyRepository vacancyRepository;
    @Autowired
    UserService userService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            User user = userService.authByToken(delegateExecution);

            if (user.getRole() != UserRole.EMPLOYER) throw new OperationNotPermitedException("Вы не работодатель");

            long id = Long.parseLong(delegateExecution.getVariable("vacancyId").toString());

            Vacancy vacancy = getVacancyById(id);
            if (vacancy != null) {
                if (Objects.equals(user.getId(), vacancy.getUser().getId())) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    delegateExecution.setVariable("result", objectMapper.writeValueAsString(responseRepository.findAllByVacancy(vacancy)));
                } else {
                    delegateExecution.setVariable("result", "Нет прав");
                }
            } else {
                delegateExecution.setVariable("result", "Резюме не существует");
            }
        } catch (Throwable throwable) {
            throw new BpmnError("error", throwable.getMessage());
        }
    }

    public Vacancy getVacancyById(long id) {
        Optional<Vacancy> vacancyOptional = vacancyRepository.findById(id);
        return vacancyOptional.orElse(null);
    }
}
