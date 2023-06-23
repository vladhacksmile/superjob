package com.vladhacksmile.searchjob.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import com.vladhacksmile.searchjob.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
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

    final private VacancyRepository vacancyRepository;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("vacancyId");
        Optional<Vacancy> vacancy = vacancyRepository.findById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        if(vacancy.isPresent()) {
            delegateExecution.setVariable("result", objectMapper.writeValueAsString(vacancy.get()));
        }
    }
}
