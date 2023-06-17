package com.vladhacksmile.searchjob.service;

import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class AsyncScheduling {//implements Job {
    @Autowired
    private MailService mailService;
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        mailService.sendSimpleMessage("vladkharin19@gmail.com", "Тема", "Текст");
//    }
}
