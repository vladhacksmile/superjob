package com.vladhacksmile.searchjob.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsyncScheduling implements Job {
    @Autowired
    private MailService mailService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        mailService.sendSimpleMessage("vladkharin19@gmail.com", "Тема", "Текст");
    }
}
