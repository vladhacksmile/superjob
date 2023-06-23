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
@Named("resumeDelete")
@RequiredArgsConstructor
public class ResumeDeleteDelegate implements JavaDelegate {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        try {
            User user = userService.authByToken(delegateExecution);

            if (user.getRole() != UserRole.EMPLOYER) throw new OperationNotPermitedException("Вы не соискатель");

            Long id = (Long) delegateExecution.getVariable("resumeId");

            Resume resume = getResumeById(id);

            if (resume != null) {
                if (Objects.equals(user.getId(), resume.getUser().getId())) {
                    resumeRepository.deleteById(id);
                    delegateExecution.setVariable("result", "Успешно удалено");
                } else {
                    delegateExecution.setVariable("result", "Нет прав");
                }
            } else {
                delegateExecution.setVariable("result", "'Элемента не существует");
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
