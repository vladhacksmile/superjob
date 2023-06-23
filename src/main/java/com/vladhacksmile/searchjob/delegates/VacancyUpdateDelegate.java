package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.enums.UserRole;
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
@Named("vacancyUpdate")
@RequiredArgsConstructor
public class VacancyUpdateDelegate implements JavaDelegate {

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            User user = userService.authByToken(delegateExecution);

            if (user.getRole() != UserRole.APPLICANT) throw new OperationNotPermitedException("Вы не работодатель");

            Long id = (Long) delegateExecution.getVariable("vacancyId");
            String name = (String) delegateExecution.getVariable("name");
            int salary = (Integer) delegateExecution.getVariable("salary");
            String information = (String) delegateExecution.getVariable("information");
            Vacancy vacancy = getVacancyById(id);
            if (vacancy != null) {
                if (Objects.equals(user.getId(), vacancy.getUser().getId())) {
                    vacancy.setName(name);
                    vacancy.setSalary(salary);
                    vacancy.setInformation(information);
                    vacancyRepository.save(vacancy);
                    delegateExecution.setVariable("result", "Успешно обновлено");
                } else {
                    delegateExecution.setVariable("result", "Нет прав");
                }
            } else {
                delegateExecution.setVariable("result", "Вакансия не существует");
            }
        } catch (Throwable throwable) {
                throw new BpmnError("error", throwable.getMessage());
        }
    }

    public Vacancy getVacancyById(long id) {
        Optional<Vacancy> resumeOptional = vacancyRepository.findById(id);
        return resumeOptional.orElse(null);
    }
}
