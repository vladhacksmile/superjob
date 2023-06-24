package com.vladhacksmile.searchjob.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladhacksmile.searchjob.dto.MailDTO;
import com.vladhacksmile.searchjob.repository.UserRepository;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.ArrayList;

@Component
@Named("scheduling")
public class SchedulingDelegate implements JavaDelegate {
    @Autowired
    KafkaTemplate<Long, MailDTO> kafkaTemplate;
    @Autowired
    UserRepository userRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            ArrayList<String> list = new ArrayList<>();
            list.add("zinchen_ko@mail.ru");

            MailDTO mailDTO = new MailDTO(list);
            kafkaTemplate.send("msg", mailDTO);

            ObjectMapper objectMapper = new ObjectMapper();
            delegateExecution.getProcessEngineServices().getRuntimeService()
                    .createMessageCorrelation("message-request")
                    .setVariable("message-emails", objectMapper.writeValueAsString(list))
                    .correlate();
        } catch (Throwable throwable) {
            throw new BpmnError("error", throwable.getMessage());
        }
    }
}
