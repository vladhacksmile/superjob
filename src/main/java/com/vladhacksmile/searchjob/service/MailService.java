package com.vladhacksmile.searchjob.service;

import com.vladhacksmile.searchjob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    UserRepository userRepository;
    public void sendSimpleMessage(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("megacheat.dev@yandex.ru");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//        ArrayList<String> result = (ArrayList<String>) userRepository.findAll().stream().map(User::getMail).collect(Collectors.toList());
    }
}
