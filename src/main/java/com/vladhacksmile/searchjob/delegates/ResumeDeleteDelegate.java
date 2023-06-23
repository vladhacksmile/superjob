package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("resumeDelete")
@RequiredArgsConstructor
public class ResumeDeleteDelegate implements JavaDelegate {

    final private ResumeRepository resumeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long id = (Long) delegateExecution.getVariable("resumeId");

        Resume resume = getResumeById(id);

        if(resume != null) {
            resumeRepository.deleteById(id);
            delegateExecution.setVariable("result", "Успешно удалено");
        } else {
            delegateExecution.setVariable("result", "'Элемента не существует");
        }
    }

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        return resumeOptional.orElse(null);
    }
}
