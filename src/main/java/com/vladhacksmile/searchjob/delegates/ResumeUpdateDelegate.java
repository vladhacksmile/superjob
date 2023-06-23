package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Optional;

@Component
@Named("resumeUpdate")
@RequiredArgsConstructor
public class ResumeUpdateDelegate implements JavaDelegate {

    final private ResumeRepository resumeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long resumeId = (Long) delegateExecution.getVariable("resumeId");
        String specialization = (String) delegateExecution.getVariable("specialization");
        String description = (String) delegateExecution.getVariable("description");
        Resume resume = getResumeById(resumeId);
        if (resume != null) {
            resume.setSpecialization(specialization);
            resume.setDescription(description);
            resumeRepository.save(resume);
            delegateExecution.setVariable("result", "Успешно обновлено");
        } else {
            delegateExecution.setVariable("result", "Резюме не существует");
        }
    }

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        return resumeOptional.orElse(null);
    }
}
