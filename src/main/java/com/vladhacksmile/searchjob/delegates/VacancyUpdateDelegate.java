package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("vacancyUpdate")
@RequiredArgsConstructor
public class VacancyUpdateDelegate implements JavaDelegate {

    final private VacancyRepository vacancyRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("vacancyId");
        String name = (String) delegateExecution.getVariable("name");
        int salary = (Integer) delegateExecution.getVariable("salary");
        String information = (String) delegateExecution.getVariable("information");
        Vacancy vacancy = getVacancyById(id);
        if (vacancy != null) {
            vacancy.setName(name);
            vacancy.setSalary(salary);
            vacancy.setInformation(information);
            vacancyRepository.save(vacancy);
            delegateExecution.setVariable("result", "Успешно обновлено");
        } else {
            delegateExecution.setVariable("result", "Резюме не существует");
        }
    }

    public Vacancy getVacancyById(long id) {
        Optional<Vacancy> resumeOptional = vacancyRepository.findById(id);
        return resumeOptional.orElse(null);
    }
}
