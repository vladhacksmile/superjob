package com.vladhacksmile.searchjob.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("resumeGet")
@RequiredArgsConstructor
public class ResumeGetDelegate implements JavaDelegate {

    final private ResumeRepository resumeRepository;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("resumeId");
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        if(resumeOptional.isPresent()) {
            delegateExecution.setVariable("result", objectMapper.writeValueAsString(resumeOptional.get()));
        }
    }
}
