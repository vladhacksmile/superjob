package com.vladhacksmile.searchjob.delegates;

import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.enums.UserRole;
import com.vladhacksmile.searchjob.repository.ResumeRepository;
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
@Named("resumeUpdate")
@RequiredArgsConstructor
public class ResumeUpdateDelegate implements JavaDelegate {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserService userService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            User user = userService.authByToken(delegateExecution);

            if (user.getRole() != UserRole.APPLICANT) throw new OperationNotPermitedException("Вы не соискатель");

            long resumeId = Long.parseLong(delegateExecution.getVariable("resumeId").toString());
            String specialization = (String) delegateExecution.getVariable("specialization");
            String description = (String) delegateExecution.getVariable("description");
            Resume resume = getResumeById(resumeId);
            if (resume != null) {
                if (Objects.equals(user.getId(), resume.getUser().getId())) {
                    resume.setSpecialization(specialization);
                    resume.setDescription(description);
                    resumeRepository.save(resume);
                    delegateExecution.setVariable("result", "Успешно обновлено");
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

    public Resume getResumeById(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        return resumeOptional.orElse(null);
    }
}
