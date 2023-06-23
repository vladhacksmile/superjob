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
@Named("vacancyDelete")
@RequiredArgsConstructor
public class VacancyDeleteDelegate implements JavaDelegate {

    final private VacancyRepository vacancyRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long id = (Long) delegateExecution.getVariable("vacancyId");

        Vacancy vacancy = getVacancyById(id);

        if(vacancy != null) {
            vacancyRepository.deleteById(id);
            delegateExecution.setVariable("result", "Успешно удалено");
        } else {
            delegateExecution.setVariable("result", "'Элемента не существует");
        }
    }

    public Vacancy getVacancyById(long id) {
        Optional<Vacancy> vacancyOptional = vacancyRepository.findById(id);
        return vacancyOptional.orElse(null);
    }
}
