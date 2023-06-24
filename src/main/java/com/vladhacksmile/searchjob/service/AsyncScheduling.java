package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.dto.MailDTO;
import com.vladhacksmile.searchjob.repository.UserRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class AsyncScheduling implements Job {

    @Autowired
    private KafkaTemplate<Long, MailDTO> kafkaTemplate;

    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("zinchen_ko@mail.ru");
//        MailDTO mailDTO = new MailDTO(list);
//        kafkaTemplate.send("msg", mailDTO);
    }
}
